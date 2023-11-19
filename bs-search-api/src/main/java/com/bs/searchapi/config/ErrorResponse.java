package com.bs.searchapi.config;

import com.bs.common.exception.ErrorType;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ErrorResponse {
    private final ErrorType errorType;
    private final String errorMessage;

    public ErrorResponse(ErrorType errorType,
                         String errorMessage) {
        this.errorType = errorType;
        this.errorMessage = errorMessage;
    }
}
