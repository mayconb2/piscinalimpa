package br.com.unicesumar.piscinalimpa;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserTypeNotAllowed extends RuntimeException {

    public UserTypeNotAllowed(String message) {
        super(message);
    }

    public UserTypeNotAllowed(String message, Throwable cause) {
        super(message, cause);
    }
}
