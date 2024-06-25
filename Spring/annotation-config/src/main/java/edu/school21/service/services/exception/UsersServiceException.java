package edu.school21.service.services.exception;

public class UsersServiceException extends RuntimeException {

    public UsersServiceException(String msg) {
        super(msg);
    }

    public UsersServiceException(String msg, Throwable err) {
        super(msg, err);
    }
}
