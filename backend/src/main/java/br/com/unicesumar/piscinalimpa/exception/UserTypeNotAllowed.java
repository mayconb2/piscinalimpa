package br.com.unicesumar.piscinalimpa.exception;

public class UserTypeNotAllowed extends RuntimeException {

    public UserTypeNotAllowed(String message) {
        super(message);
    }

    public UserTypeNotAllowed(String message, Throwable cause) {
        super(message, cause);
    }
}
