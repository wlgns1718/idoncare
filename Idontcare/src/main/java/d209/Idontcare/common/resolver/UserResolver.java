package d209.Idontcare.common.resolver;

import d209.Idontcare.common.annotation.LoginUser;
import d209.Idontcare.user.entity.User;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Component
public class UserResolver implements HandlerMethodArgumentResolver {
  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.getParameterAnnotation(LoginUser.class) != null;
  }
  
  @Override
  public User resolveArgument(MethodParameter parameter,
                                ModelAndViewContainer mavContainer,
                                NativeWebRequest webRequest,
                                WebDataBinderFactory binderFactory)
                                  throws Exception {
    HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
    
    User user = (User)request.getAttribute("user");
    
    return user;
  }
}
