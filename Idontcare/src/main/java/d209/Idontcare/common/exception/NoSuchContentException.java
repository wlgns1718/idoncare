package d209.Idontcare.common.exception;

public class NoSuchContentException extends CommonException {
  
  public final static String CODE = "405";
  public final static String DESCRIPTION = "컨텐츠 찾기 실패";
  
  public NoSuchContentException(){
    this.code = Integer.parseInt(CODE);
    this.message = "해당 컨텐츠를 찾을 수 없습니다";
  }
  
  public NoSuchContentException(String message){
    this.code = Integer.parseInt(CODE);
    this.message = message;
  }
}

