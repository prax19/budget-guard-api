package com.prax19.exceptions.budget;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BudgetNotFoundException extends RuntimeException {

    public BudgetNotFoundException() {
        super();
    }

    public BudgetNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BudgetNotFoundException(String message) {
        super(message);
    }

}
