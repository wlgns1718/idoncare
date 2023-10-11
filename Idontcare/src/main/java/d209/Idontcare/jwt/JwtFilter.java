package d209.Idontcare.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import d209.Idontcare.common.MutableHttpServletRequest;
import d209.Idontcare.common.dto.ResponseDto;
import d209.Idontcare.common.exception.CommonException;
import d209.Idontcare.common.exception.ExpiredTokenException;
import d209.Idontcare.common.exception.InternalServerException;
import d209.Idontcare.user.repository.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
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
  
  private JwtTokenProvider jwtTokenProvider;
  private final ObjectMapper mapper =  new ObjectMapper();
  private Long refreshExpirationTime;
  
  public JwtFilter(JwtTokenProvider jwtTokenProvider,
                   Long refreshExpirationTime){
    this.jwtTokenProvider = jwtTokenProvider;
    this.refreshExpirationTime = refreshExpirationTime;
  }
  
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
  protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                  HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {
    
    MutableHttpServletRequest request = new MutableHttpServletRequest(httpServletRequest);
    
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
      
      boolean refreshTokenFinded = false;
      Cookie[] cookies = request.getCookies();
      
      if(cookies == null || cookies.length == 0){
        throw new ExpiredTokenException("리프레시 토큰이 만료되었습니다");
      }
      for(Cookie cookie: cookies){
        if(cookie.getName().equals("refreshToken")){
          refreshTokenFinded = true;  //리프레시 토큰 찾았다고 표시
          //Refresh Token이면
          String refreshToken = cookie.getValue();
          
          AccessRefreshTokenDto created = null;
          try{
            created = jwtTokenProvider.refresh(refreshToken);
          } catch(ExpiredJwtException ex){
            //Refresh Token이 만료된 경우
            throw new ExpiredTokenException("리프레시 토큰이 만료되었습니다");
          }
          
          //Refresh Token을 이용해 새로 발급해주자
          String createdAccessToken = created.getAccessToken();
          String createdRefreshToken = created.getRefreshToken();
          
          //새로 발급된 토큰을 기준으로 유저 정보를 request에 넣어주자
          AuthInfo authInfo = jwtTokenProvider.getAuthInfo(createdAccessToken);
          request.setAttribute("userId", authInfo.getUserId()); //정보 담아서 보내기
          request.setAttribute("role", authInfo.getRole());    //정보 담아서 보내기
          
          //발급된 토큰들을 헤더와 쿠키에 넣어주자
          response.addHeader("Authorization", "Bearer " + createdAccessToken);
          ResponseCookie createdCookie = ResponseCookie.from("refreshToken", createdRefreshToken)
              .path("/")
              .sameSite("None")
              .secure(true)
//            .httpOnly(true)
              .domain("j9d209.p.ssafy.io")
              .maxAge(refreshExpirationTime / 1000)
              .build();
          response.addHeader("set-cookie", createdCookie.toString());
        }
      }
      
      if( !refreshTokenFinded ){
        //만약 리프레시 토큰을 못 찾았으면
        throw new ExpiredTokenException("리프레시 토큰이 만료되었습니다");
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
