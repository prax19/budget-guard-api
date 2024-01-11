package com.prax19.exceptions.budget;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class NoBudgetAccessException extends RuntimeException{

    public NoBudgetAccessException() {
        super();
    }

    public NoBudgetAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoBudgetAccessException(String message) {
        super(message);
    }

}
