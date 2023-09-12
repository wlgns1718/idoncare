package d209.Idontcare.common.exception;

import lombok.Getter;

@Getter
public class CommonException extends RuntimeException{
  protected int code;
  protected String message;
}
