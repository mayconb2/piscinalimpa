package br.com.unicesumar.piscinalimpa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ConstraintException extends RuntimeException {

    public ConstraintException(String message) {super(message);}
}
