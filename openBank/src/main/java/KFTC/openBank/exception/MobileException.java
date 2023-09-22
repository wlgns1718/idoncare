package KFTC.openBank.exception;

public class MobileException extends RuntimeException{

    public MobileException(String message) {
        super(message);
    }

    public static class NotCorrect extends MobileException {
        public NotCorrect(String message) {
            super(message);
        }
    }

}
