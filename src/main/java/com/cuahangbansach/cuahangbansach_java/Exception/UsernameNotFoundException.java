package com.cuahangbansach.cuahangbansach_java.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UsernameNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public UsernameNotFoundException(String message) {
        super(message);
    }
}
