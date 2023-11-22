package d209.Idontcare.account.exception;

import d209.Idontcare.common.exception.CommonException;

public class VirtualAccountException extends CommonException{

    public final static String CODE = "411";
    public final static String DESCRIPTION = "계좌 에러";
    
    public VirtualAccountException(String message){
        this.code = 411;
        this.message = message;
    }

    public VirtualAccountException(int code, String message){
        this.code = code;
        this.message = message;
    }
}
