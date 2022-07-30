package com.antoniocmoura.cloudparking.infrastructure.configuration.exception;

import com.antoniocmoura.cloudparking.domain.exceptions.DomainException;
import com.antoniocmoura.cloudparking.domain.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(final NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiError.from(ex));
    }

    @ExceptionHandler(value = DomainException.class)
    public ResponseEntity<?> handleDomainException(final DomainException ex) {
        return ResponseEntity.unprocessableEntity().body(ApiError.from(ex));
    }

    record ApiError(Date timestamp, String status, int statusCode, String message ) {
        static ApiError from(final DomainException ex) {
            return new ApiError(
                    new Date(),
                    ex.getStatusText(),
                    ex.getStatusCode(),
                    ex.getMessage()
            );
        }
    }
}
