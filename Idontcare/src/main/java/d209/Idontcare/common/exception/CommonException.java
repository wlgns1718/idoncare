package d209.Idontcare.common.exception;

import lombok.Getter;

@Getter
public class CommonException extends RuntimeException{
  public int code;
  public String message;

}
