package com.laurent.infrasoko.common.exception;

import com.laurent.infrasoko.common.BaseException;
import org.springframework.http.HttpStatus;

import java.util.List;

public class InvalidOperationException extends BaseException {
    private final static HttpStatus status = HttpStatus.BAD_REQUEST;
    public InvalidOperationException(String message) {
        super(message, status);
    }

    public InvalidOperationException(String message, HttpStatus status) {
        super(message, status);
    }

    public InvalidOperationException(HttpStatus status, String message, List<String> details) {
        super(status, message, details);
    }
}