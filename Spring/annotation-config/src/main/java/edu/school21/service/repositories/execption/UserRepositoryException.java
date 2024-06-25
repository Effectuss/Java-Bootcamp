package edu.school21.service.repositories.execption;

public class UserRepositoryException extends RuntimeException {
    public UserRepositoryException(String msg) {
        super(msg);
    }

    public UserRepositoryException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
