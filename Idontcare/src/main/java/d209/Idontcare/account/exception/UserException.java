package d209.Idontcare.account.exception;

import d209.Idontcare.common.exception.CommonException;

public class UserException extends CommonException{

    public final static String CODE = "400";
    public final static String DESCRIPTION = "로그인 되지 않은 접근";

    public UserException(int code, String message){
        this.code = code;
        this.message = message;
    }

    public static class UserNotFoundException extends UserException{
        public UserNotFoundException(int code, String message) {
            super(code, message);
        }
    }
}
