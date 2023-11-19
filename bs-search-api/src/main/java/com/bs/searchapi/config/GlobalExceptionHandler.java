package com.bs.searchapi.config;

import com.bs.common.exception.ApiException;
import com.bs.common.exception.ErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ApiException.class})
    public ResponseEntity<ErrorResponse> handleApiException(ApiException e) {
        log.error("api exception occurred. message=[{}]. className=[{}]", e.getErrorMessage(), e.getClass().getName());
        return ResponseEntity.status(e.getHttpStatus())
                .body(new ErrorResponse(e.getErrorType(), e.getErrorMessage()));
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("unknown error occurred. message=[{}]. className=[{}]", e.getMessage(), e.getClass().getName(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(ErrorType.UNKNOWN, ErrorType.UNKNOWN.getDescription()));
    }

    @ExceptionHandler({BindException.class})
    public ResponseEntity<ErrorResponse> bindException(BindException e) {
        log.error("binding error occurred. message=[{}]. className=[{}]", e.getMessage(), e.getClass().getName(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(ErrorType.INVALID_PARAMETER, e.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.joining(" "))));
    }
}
