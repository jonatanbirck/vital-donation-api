package com.univates.vitaldonationapi.domain.exception.security;

public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException() {
        super("invalid token");
    }

    public InvalidTokenException(String message) {
        super(message);
    }

}
