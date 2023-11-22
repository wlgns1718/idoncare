package d209.Idontcare.common.exception;

public class AuthorizationException extends CommonException {
  
  public final static String CODE = "406";
  public final static String DESCRIPTION = "권한 없음";
  
  public AuthorizationException(){
    this.code = Integer.parseInt(CODE);
    this.message = "해당 요청에 대한 권한이 없습니다";
  }
  
  public AuthorizationException(String message){
    this.code = Integer.parseInt(CODE);
    this.message = message;
  }
}
