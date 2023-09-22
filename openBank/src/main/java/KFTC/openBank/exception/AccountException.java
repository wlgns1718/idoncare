package KFTC.openBank.exception;

import org.webjars.NotFoundException;

public class AccountException extends RuntimeException{

    public AccountException(String message) {
        super(message);
    }
    
    //핀테크 이용 번호가 등록 되어 있지 않을 때
    public static class FintechNumNotFoundException extends AccountException{
        public FintechNumNotFoundException(String message) {
            super(message);
        }
    }

    //계좌가 없는 계좌일 경우
    public static class AccoutNotFoundException extends AccountException{
        public AccoutNotFoundException(String message) {
            super(message);
        }
    }


    //계좌 잔액이 부족할 경우
    public static class AccoutInsufficientException extends AccountException{
        public AccoutInsufficientException(String message) {
            super(message);
        }
    }
}
