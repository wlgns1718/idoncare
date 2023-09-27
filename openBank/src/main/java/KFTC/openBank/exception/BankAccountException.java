package KFTC.openBank.exception;

public class BankAccountException extends RuntimeException{
    public BankAccountException(String message) {
        super(message);
    }

    public static class NotFoundException extends BankAccountException {
        public NotFoundException(String message) {
            super(message);
        }
    }

    public static class DepositException extends BankAccountException {
        public DepositException(String message) {
            super(message);
        }
    }

    public static class WithdrawException extends BankAccountException {
        public WithdrawException(String message) {
            super(message);
        }
    }
}
