package edu.school21.chat.repositories.exception;

public class NotSavedSubEntityException extends RuntimeException {
    public NotSavedSubEntityException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NotSavedSubEntityException(String msg) {
        super(msg);
    }

    public NotSavedSubEntityException(Throwable cause) {
        super(cause);
    }
}
