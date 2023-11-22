package d209.Idontcare.common.exception;

/**
 * 로그인 되지 않는 사용자의 접근
 */
public class AuthenticationException extends CommonException{
  
  public final static String CODE = "400";
  public final static String DESCRIPTION = "로그인 되지 않은 접근";
  
  public AuthenticationException(){
    this.code = Integer.parseInt(CODE);
    this.message = "로그인 된 사용자만 접근할 수 있습니다";
  }
  
  public AuthenticationException(String message){
    this.code = Integer.parseInt(CODE);
    this.message = message;
  }
}
