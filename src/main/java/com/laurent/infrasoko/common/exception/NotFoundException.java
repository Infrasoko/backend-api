package com.laurent.infrasoko.common.exception;

import com.laurent.infrasoko.common.BaseException;
import org.springframework.http.HttpStatus;

import java.util.List;

public class NotFoundException extends BaseException {
    private final static HttpStatus status = HttpStatus.NOT_FOUND;

    public NotFoundException(String message) {
        super(message, status);
    }

    public NotFoundException(String message, List<String> details) {
        super(status, message, details);
    }
}
