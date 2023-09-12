package d209.Idontcare.common.exception;

public class MemberException extends CommonException{
  
  public final static String CODE = "400";
  public final static String DESCRIPTION = "로그인 되지 않은 접근";
  
  public MemberException(){
    this.code = 400;
    this.message = "로그인 된 사용자만 접근할 수 있습니다";
  }
  
  public MemberException(String message){
    this.code = 400;
    this.message = message;
  }
}
