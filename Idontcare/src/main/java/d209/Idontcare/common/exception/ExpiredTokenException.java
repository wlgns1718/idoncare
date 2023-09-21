package d209.Idontcare.common.exception;

public class ExpiredTokenException extends CommonException {
  
  public final static String CODE = "410";
  public final static String DESCRIPTION = "Access Token 만료";
  
  public ExpiredTokenException(){
    this.code = Integer.parseInt(CODE);
    this.message = "Access Token이 만료되었습니다";
  }
  
  public ExpiredTokenException(String message){
    this.code = Integer.parseInt(CODE);
    this.message = message;
  }
}