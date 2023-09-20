package d209.Idontcare.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import d209.Idontcare.common.dto.ResponseDto;
import d209.Idontcare.common.exception.CommonException;
import d209.Idontcare.common.exception.InternalServerException;
import d209.Idontcare.user.entity.User;
import d209.Idontcare.user.repository.UserRepository;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class JwtFilter extends OncePerRequestFilter {
  
  private final JwtTokenProvider jwtTokenProvider;
  private final UserRepository userRepository;
  private final ObjectMapper mapper =  new ObjectMapper();
  
  private Set<String> acceptPath;
  
  {
    acceptPath = new HashSet<>();
    acceptPath.add("/api/user/login");
    acceptPath.add("/api/user/regist");
  }

  public JwtFilter(JwtTokenProvider jwtTokenProvider, UserRepository userRepository){
    this.jwtTokenProvider = jwtTokenProvider;
    this.userRepository = userRepository;
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
        Long userId = jwtTokenProvider.getUserId(accessToken);
        User user = userRepository.findById(userId).orElseThrow(InternalServerException::new);
        request.setAttribute("user", user); //정보 담아서 보내기
      }
    } catch(CommonException e){
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
