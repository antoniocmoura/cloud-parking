package com.antoniocmoura.cloudparking.domain.exceptions;

public class NotBlankException extends DomainException {

    public NotBlankException(String field) {
        super(422, "validation error", String.format("'%s' should not be empty", field));
    }
}