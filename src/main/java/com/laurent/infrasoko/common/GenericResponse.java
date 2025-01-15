package com.laurent.infrasoko.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL) // Exclude null fields in the serialized response
public class GenericResponse<T> {

    private boolean success;
    private String message;
    private T data;
    private List<String> errors;
    private int status;

    protected GenericResponse(T data, HttpStatus status, String message, List<String> errors) {
        this.status = (status == null) ? HttpStatus.OK.value() : status.value();
        this.success = HttpStatus.valueOf(this.status).is2xxSuccessful();

        if (message != null)
            this.message = message;
        else
            this.message = (this.success) ? "Operation succeeded" : "Operation failed";

        this.data = data;
        this.errors = errors;
    }

    public static class Builder<T> {
        private String message;
        private T data;
        private HttpStatus status;

        private List<String> errors;

        public Builder() {
        }

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public Builder<T> status(HttpStatus status) {
            this.status = status;
            return this;
        }

        public Builder<T> errors(List<String> errors) {
            this.errors = errors;
            return this;
        }

        public GenericResponse<T> build() {
            return new GenericResponse<T>(data, status, message, errors);
        }
    }
}
