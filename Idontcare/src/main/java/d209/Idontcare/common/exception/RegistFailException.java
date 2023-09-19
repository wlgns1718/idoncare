package d209.Idontcare.common.exception;

public class RegistFailException extends CommonException{
    public final static String CODE = "502";
    public final static String DESCRIPTION = "정보 등록 실패";

    public RegistFailException(){
        this.code = Integer.parseInt(CODE);
        this.message = "정보를 등록하는데 실패했습니다.";
    }

    public RegistFailException(String message){
        this.code = Integer.parseInt(CODE);
        this.message = message;
    }
}
