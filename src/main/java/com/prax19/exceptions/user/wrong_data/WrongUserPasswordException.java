package com.prax19.exceptions.user.wrong_data;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class WrongUserPasswordException extends WrongUserDataException {

    public WrongUserPasswordException() {
        super("wrong password");
    }

    public WrongUserPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongUserPasswordException(String message) {
        super(message);
    }

}

