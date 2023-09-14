package KFTC.openBank.exception;

public class BackAccountException extends RuntimeException{
    public BackAccountException(String message) {
        super(message);
    }

    public static class DepositException extends BackAccountException{
        public DepositException(String message) {
            super(message);
        }
    }

    public static class WithdrawException extends BackAccountException{
        public WithdrawException(String message) {
            super(message);
        }
    }
}
