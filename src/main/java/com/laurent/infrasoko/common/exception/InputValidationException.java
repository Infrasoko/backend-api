package com.laurent.infrasoko.common.exception;

import com.laurent.infrasoko.common.BaseException;
import org.springframework.http.HttpStatus;

import java.util.List;

public class InputValidationException extends BaseException {
  private static final HttpStatus status = HttpStatus.BAD_REQUEST;

  public InputValidationException(String message) {
    super(message, status);
  }

  public InputValidationException(String message, List<String> details) {
    super(status, message, details);
  }
}
