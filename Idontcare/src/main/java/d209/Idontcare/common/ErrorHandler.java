package d209.Idontcare.common;

import d209.Idontcare.common.exception.BadRequestException;
import d209.Idontcare.common.exception.CommonException;
import org.springframework.validation.BindingResult;

public class ErrorHandler {
  
  public static void ErrorHandling(BindingResult bindingResult) throws CommonException {
    if(bindingResult.hasErrors()){
      throw new BadRequestException(bindingResult.getAllErrors().get(0).getDefaultMessage());
    }
  }
}
