package com.laurent.infrasoko.common.exception;

import com.laurent.infrasoko.common.BaseException;
import org.springframework.http.HttpStatus;

public class UnauthorizedOperationException extends BaseException {
    public UnauthorizedOperationException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}