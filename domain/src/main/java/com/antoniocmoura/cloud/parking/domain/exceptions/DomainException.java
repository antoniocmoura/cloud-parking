package com.antoniocmoura.cloud.parking.domain.exceptions;

public class DomainException extends RuntimeException {

    private final int statusCode;
    private final String statusText;

    public DomainException(int statusCode, String statusText, String message) {
        super(message);
        this.statusCode = statusCode;
        this.statusText = statusText;
    }

    public DomainException(String message) {
        super(message);
        this.statusCode = 400;
        this.statusText = "error";
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusText() {
        return statusText;
    }

}
