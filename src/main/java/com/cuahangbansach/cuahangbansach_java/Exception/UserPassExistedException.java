package com.cuahangbansach.cuahangbansach_java.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FOUND)
public class UserPassExistedException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public UserPassExistedException(String message) {
        super(message);
    }
}
