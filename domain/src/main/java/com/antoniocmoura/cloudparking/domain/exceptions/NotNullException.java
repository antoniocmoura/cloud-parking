package com.antoniocmoura.cloudparking.domain.exceptions;

public class NotNullException extends DomainException {

    public NotNullException(String field) {
        super(422, "validation error", String.format("'%s' should not be null", field));
    }

}
