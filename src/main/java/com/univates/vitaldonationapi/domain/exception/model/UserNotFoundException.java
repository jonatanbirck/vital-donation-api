package com.univates.vitaldonationapi.domain.exception.model;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("user not found");
    }

    public UserNotFoundException(String message) {
        super(message);
    }

}
