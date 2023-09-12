package d209.Idontcare.common.dto;

import d209.Idontcare.common.exception.CommonException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResponseDto<T> {
  private int code;
  private String error;
  private T data;
  
  public static <D> ResponseDto success(D data){
    ResponseDto<D> result = new ResponseDto<D>();
    result.code = 200;
    result.data = data;
    
    return result;
  }
  
  public static ResponseDto<Void> fail(CommonException e){
    ResponseDto<Void> result = new ResponseDto<>();
    result.code = e.getCode();
    result.error = e.getMessage();
    
    return result;
  }
}
