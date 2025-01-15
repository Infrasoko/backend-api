package com.laurent.infrasoko.common.exception;

import com.laurent.infrasoko.common.BaseException;
import org.springframework.http.HttpStatus;

public class NotificationException extends BaseException {
    public NotificationException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
