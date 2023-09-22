package KFTC.openBank.exception;

public class UserException extends RuntimeException{

    public UserException(String message) {
        super(message);
    }

    public static class AlreadySaveException extends UserException{
        public AlreadySaveException(String message) {
            super(message);
        }
    }
}
