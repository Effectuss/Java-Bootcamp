package ex04;

public class IllegalUserBalanceException extends RuntimeException {
    public IllegalUserBalanceException(String message) {
        super(message);
    }
}
