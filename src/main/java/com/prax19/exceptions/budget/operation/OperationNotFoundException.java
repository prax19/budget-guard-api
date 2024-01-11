package com.prax19.exceptions.budget.operation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class OperationNotFoundException extends NoSuchElementException {

    public OperationNotFoundException() {
        super();
    }

    public OperationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public OperationNotFoundException(String message) {
        super(message);
    }

}
