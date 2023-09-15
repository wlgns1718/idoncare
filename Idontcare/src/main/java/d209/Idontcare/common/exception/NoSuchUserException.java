package d209.Idontcare.common.exception;

public class NoSuchUserException extends CommonException {
  
  public final static String CODE = "403";
  public final static String DESCRIPTION = "유저 찾기 실패";
  
  public NoSuchUserException(){
    this.code = Integer.parseInt(CODE);
    this.message = "해당 유저를 찾을 수 없습니다";
  }
  
  public NoSuchUserException(String message){
    this.code = Integer.parseInt(CODE);
    this.message = message;
  }
}
