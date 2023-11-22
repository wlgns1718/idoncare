package d209.Idontcare.common.aspect;

import d209.Idontcare.common.dto.ResponseDto;
import d209.Idontcare.common.exception.BadRequestException;
import d209.Idontcare.common.exception.CommonException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionAspect {
  
  @Around("execution(public d209.Idontcare.common.dto.ResponseDto *(..))")
  public Object around(ProceedingJoinPoint pjp){
    
    try{
      return pjp.proceed();
    } catch(CommonException e){
      return ResponseDto.fail(e);
    }
    catch(Throwable e){
      System.out.println(e.getMessage());
      return ResponseDto.fail(e);
    }
  }
}
