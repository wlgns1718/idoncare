package d209.Idontcare.common.exception;

public class MustParentException extends CommonException {
  
  public final static String CODE = "401";
  public final static String DESCRIPTION = "부모 사용자가 아닌 경우";
  
  public MustParentException(){
    this.code = Integer.parseInt(CODE);
    this.message = "부모만 해당 요청을 수행할 수 있습니다";
  }
  
  public MustParentException(String message){
    this.code = Integer.parseInt(CODE);
    this.message = message;
  }
}
