package com.bs.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final ErrorType errorType;
    private final String errorMessage;

    public ApiException(HttpStatus httpStatus,
                        ErrorType errorType,
                        String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorType = errorType;
        this.errorMessage = errorMessage;
    }
}
