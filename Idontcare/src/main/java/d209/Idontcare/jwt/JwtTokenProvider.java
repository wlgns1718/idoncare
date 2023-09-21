package d209.Idontcare.jwt;

import d209.Idontcare.common.exception.AuthenticationException;
import d209.Idontcare.common.exception.ExpiredTokenException;
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

  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.access-expiration-time}")
  private Long accessExpirationTime;
  
  @Value("${jwt.refresh-expiration-time}")
  private Long refreshExpirationTime;
  
  /* Access 토큰 생생 */
  public String createAccessToken(Long userId){
    Claims claims = Jwts.claims();
    claims.put("userId", userId);
    
    Date now = new Date();
    Date expireTime = new Date(now.getTime() + accessExpirationTime);
    
    String accessToken = Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(expireTime)
        .signWith(SignatureAlgorithm.HS256, secret)
        .compact();
    
    return accessToken;
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
  
  public Long getUserId(String token){
    Long userId = (Long)Jwts.parser()
        .setSigningKey(secret)
        .parseClaimsJws(token)
        .getBody()
        .get("userId");
    
    return userId;
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
}
