package d209.Idontcare.common.aspect;

import d209.Idontcare.common.annotation.LoginOnly;
import d209.Idontcare.common.exception.AuthorizationException;
import d209.Idontcare.common.exception.MustChildException;
import d209.Idontcare.common.exception.MustParentException;
import d209.Idontcare.jwt.AuthInfo;
import d209.Idontcare.user.entity.Role;
import d209.Idontcare.user.entity.User;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import d209.Idontcare.common.exception.AuthenticationException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Aspect
@Component
public class LoginAspect {
  
  @Before("@annotation(loginOnly)")
  public void LoginOnly(LoginOnly loginOnly) throws IOException {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
        .getRequest();
    
    Cookie[] testCookies = request.getCookies();
    if(testCookies != null){
      for(Cookie c: testCookies){
        System.out.printf("Cookie(%s) : %s\n", c.getName(), c.getValue());
      }
    }
    
    Role role;
    try{
      role = (Role)request.getAttribute("role");
      if(role == null) throw new AuthenticationException();
    } catch(Exception e){
      throw new AuthenticationException();
    }
    
    switch(loginOnly.level()){
      case PARENT_ONLY:
        if(role != Role.PARENT) throw new MustParentException();
        break;
      case CHILD_ONLY:
        if(role != Role.CHILD) throw new MustChildException();
        break;
      case PARENT_OR_CHILD:
        if(role != Role.PARENT && role != Role.CHILD) throw new AuthorizationException();
        break;
    }
  }
}
