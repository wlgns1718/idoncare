package d209.Idontcare.common.exception;

public class MustChildException extends CommonException{
  
  public final static String CODE = "402";
  public final static String DESCRIPTION = "아이 사용자가 아닌 경우";
  
  public MustChildException(){
    this.code = Integer.parseInt(CODE);
    this.message = "아이만 해당 요청을 수행할 수 있습니다";
  }
  
  public MustChildException(String message){
    this.code = Integer.parseInt(CODE);
    this.message = message;
  }
}
