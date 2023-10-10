package d209.Idontcare.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import d209.Idontcare.common.dto.ResponseDto;
import d209.Idontcare.common.exception.CommonException;
import d209.Idontcare.common.exception.ExpiredTokenException;
import d209.Idontcare.common.exception.InternalServerException;
import d209.Idontcare.user.repository.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class JwtFilter extends OncePerRequestFilter {
  
  private final JwtTokenProvider jwtTokenProvider;
  private final ObjectMapper mapper =  new ObjectMapper();
  
  @Value("${jwt.refresh-expiration-time}")
  private Long refreshExpirationTime;
  
  private Set<String> acceptPath;
  
  {
    acceptPath = new HashSet<>();
    acceptPath.add("/api/user/login");
    acceptPath.add("/api/user/login/test");
    acceptPath.add("/api/user/regist");
    acceptPath.add("/api/user/refresh");
  }

  public JwtFilter(JwtTokenProvider jwtTokenProvider){
    this.jwtTokenProvider = jwtTokenProvider;
  }
  
  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {
    
    String path = request.getServletPath();
    if(acceptPath.contains(path)){
      //통과되어야 되는 경우
      filterChain.doFilter(request, response);
      return;
    }
    
    
    String accessToken = jwtTokenProvider.getBearerToken(request);  //Header에서 Bearer 토큰(액세스 토큰)가져오기
    try{
      if(accessToken != null && jwtTokenProvider.validateToken(accessToken)){
        //Access Token이 제대로 있으면
        AuthInfo authInfo = jwtTokenProvider.getAuthInfo(accessToken);
        request.setAttribute("userId", authInfo.getUserId()); //정보 담아서 보내기
        request.setAttribute("role", authInfo.getRole());    //정보 담아서 보내기
      }
    } catch(ExpiredJwtException e){
      //Access Token이 만료된 경우
      Cookie[] cookies = request.getCookies();
      for(Cookie cookie: cookies){
        if(cookie.getName().equals("refreshToken")){
          //Refresh Token이면
          String refreshToken = cookie.getValue();
          
          AccessRefreshTokenDto created = null;
          try{
            created = jwtTokenProvider.refresh(refreshToken);
          } catch(ExpiredJwtException ex){
            //Refresh Token이 만료된 경우
            throw new ExpiredTokenException();
          }
          
          String createdAccessToken = created.getAccessToken();
          String createdRefreshToken = created.getRefreshToken();
          
          response.addHeader("Authorization", "Bearer " + createdAccessToken);
          Cookie createdCookie = new Cookie("refreshToken", createdRefreshToken);
          createdCookie.setMaxAge((int)(refreshExpirationTime / 1000));
          createdCookie.setDomain("j9d209.p.ssafy.io");
          response.addCookie(createdCookie);
        }
      }
    }
    
    catch(CommonException e){
      ResponseDto<Void> result = ResponseDto.fail(e);
      String json = mapper.writeValueAsString(result);
      
      response.setCharacterEncoding("UTF-8");
      response.getWriter().write(json);
      return;
    }
    catch(Exception e){
      ResponseDto<Void> result = ResponseDto.fail(new InternalServerException());
      String json = mapper.writeValueAsString(result);
      
      response.setCharacterEncoding("UTF-8");
      response.getWriter().write(json);
      return;
    }
    
    filterChain.doFilter(request, response);
  }
}
