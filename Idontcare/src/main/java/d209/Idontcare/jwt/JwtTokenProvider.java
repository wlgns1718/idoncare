package d209.Idontcare.jwt;

import d209.Idontcare.common.exception.AuthenticationException;
import d209.Idontcare.common.exception.BadRequestException;
import d209.Idontcare.common.exception.ExpiredTokenException;
import d209.Idontcare.user.entity.Role;
import d209.Idontcare.user.entity.User;
import d209.Idontcare.user.repository.UserRepository;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
  
  private final RedisTemplate<String, String> redisTemplate;
  private final UserRepository userRepository;

  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.access-expiration-time}")
  private Long accessExpirationTime;
  
  @Value("${jwt.refresh-expiration-time}")
  private Long refreshExpirationTime;
  
  /* Access 토큰 생생 */
  public String createAccessToken(Long userId, Role role){
    Claims claims = Jwts.claims();
    claims.put("userId", userId);
    claims.put("role", role);
    
    Date now = new Date();
    Date expireTime = new Date(now.getTime() + accessExpirationTime);
    
    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(expireTime)
        .signWith(SignatureAlgorithm.HS256, secret)
        .compact();
  }
  
  /* Refresh 토큰 생성 */
  public String createRefreshToken(Long userId){
    Claims claims = Jwts.claims();
    claims.put("userId", userId);
    
    Date now = new Date();
    Date expireTime = new Date(now.getTime() + refreshExpirationTime);
    
    String refreshToken = Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(expireTime)
        .signWith(SignatureAlgorithm.HS256, secret)
        .compact();
    
    // 리프레시 토큰을 Redis에 저장
    redisTemplate.opsForValue()
        .set(
            String.valueOf(userId),
            refreshToken,
            refreshExpirationTime,
            TimeUnit.MICROSECONDS
        );
    
    return refreshToken;
  }
  
  public AuthInfo getAuthInfo(String token){
    Claims claims = Jwts.parser()
        .setSigningKey(secret)
        .parseClaimsJws(token)
        .getBody();

    Role role =  Role.valueOf((String)claims.get("role"));
    
    AuthInfo authInfo = new AuthInfo();
    authInfo.setUserId((Long)claims.get("userId"));
    authInfo.setRole(role);

    return authInfo;
  }
  
  public Long getUserId(String refreshToken){
    Claims claims = Jwts.parser()
        .setSigningKey(secret)
        .parseClaimsJws(refreshToken)
        .getBody();
    
    return (Long)claims.get("userId");
  }
  
  /* Header에서 Bearer 토큰을 가져온다 */
  public String getBearerToken(HttpServletRequest req){
    String bearerToken = req.getHeader("Authorization");
    if(bearerToken != null && bearerToken.startsWith("Bearer ")){
      return bearerToken.substring(7);
    }
    
    return null;
  }
  
  /* Access Token 검증 */
  public boolean validateToken(String token){
    try{
      Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
      return true;
    } catch(ExpiredJwtException e){
      throw new ExpiredTokenException();
    } catch(JwtException e){
      throw new AuthenticationException();
    }
  }
  
  /* Refresh Token으로 AccessToken, RefreshToken 발급 */
  public AccessRefreshTokenDto refresh(String refreshToken){
    Long userId = getUserId(refreshToken);
    String savedRefreshToken = redisTemplate.opsForValue().get(String.valueOf(userId));
    
    if(savedRefreshToken == null){
      //저장되어 있던 적이 없으면
      throw new BadRequestException("잘못 된 토큰입니다");
    }
    
    if(!refreshToken.equals(savedRefreshToken)){
      throw new BadRequestException("변조 된 토큰입니다");
    }
    
    User user = userRepository.findById(userId).orElseThrow(BadRequestException::new);
    
    String newAccessToken = createAccessToken(userId, user.getRole());
    String newRefreshToken = createRefreshToken(userId);
    
    AccessRefreshTokenDto result = new AccessRefreshTokenDto();
      result.setAccessToken(newAccessToken);
      result.setRefreshToken(newRefreshToken);

    return result;
  }
}
