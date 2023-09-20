package d209.Idontcare.common.exception;

public class InternalServerException extends CommonException{
  public final static String CODE = "500";
  public final static String DESCRIPTION = "내부 서버 에러";
  
  public InternalServerException(){
    this.code = Integer.parseInt(CODE);
    this.message = "서버 내부에 오류가 발생하였습니다.";
  }
  
  public InternalServerException(String message){
    this.code = Integer.parseInt(CODE);
    this.message = message;
  }
}