package d209.Idontcare.common.exception;

public class BadRequestException extends CommonException {
  
  public final static String CODE = "407";
  public final static String DESCRIPTION = "잘못 된 요청";
  
  public BadRequestException(){
    this.code = Integer.parseInt(CODE);
    this.message = "잘못 된 요청입니다";
  }
  
  public BadRequestException(String message){
    this.code = Integer.parseInt(CODE);
    this.message = message;
  }
}