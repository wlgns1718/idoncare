package d209.Idontcare.common.exception;



public class UpdateFailException extends CommonException{

    private final static String CODE = "502";
    private final static String DESCRIPTION = "정보 업데이트 실패";

    public UpdateFailException(){
        this.code = Integer.parseInt(CODE);
        this.message = "정보를 수정하는데 실패했습니다.";
    }

    public UpdateFailException(String message){
        this.code = Integer.parseInt(CODE);
        this.message = message;
    }
}
