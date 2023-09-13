package d209.Idontcare.common.exception;

public class DuplicatedException  extends CommonException {
  
  public final static String CODE = "404";
  public final static String DESCRIPTION = "중복된 데이터 에러";
  
  public DuplicatedException(){
    this.code = Integer.parseInt(CODE);
    this.message = "중복된 요청";
  }
  
  public DuplicatedException(String message){
    this.code = Integer.parseInt(CODE);
    this.message = message;
  }
}
