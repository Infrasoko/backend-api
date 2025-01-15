package com.laurent.infrasoko.common.exception;

import com.laurent.infrasoko.common.BaseException;
import org.springframework.http.HttpStatus;

import java.util.List;

public class FileStorageException extends BaseException {
    private static final HttpStatus status = HttpStatus.BAD_REQUEST;

    public FileStorageException(String message) {
        super(message, status);
    }

    public FileStorageException(String message, List<String> details) {
        super(status, message, details);
    }
}
