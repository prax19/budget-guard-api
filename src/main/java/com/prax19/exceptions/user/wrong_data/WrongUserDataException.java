package com.prax19.exceptions.user.wrong_data;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class WrongUserDataException extends RuntimeException {

    public WrongUserDataException() {
        super("wrong user data");
    }

    public WrongUserDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongUserDataException(String message) {
        super(message);
    }

}
