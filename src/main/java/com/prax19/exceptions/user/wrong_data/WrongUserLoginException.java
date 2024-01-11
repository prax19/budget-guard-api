package com.prax19.exceptions.user.wrong_data;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class WrongUserLoginException extends WrongUserDataException {

    public WrongUserLoginException() {
        super("wrong email");
    }

    public WrongUserLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongUserLoginException(String message) {
        super(message);
    }

}
