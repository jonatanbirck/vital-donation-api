package com.univates.vitaldonationapi.domain.exception.model;

import com.univates.vitaldonationapi.domain.entity.User;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException() {
        super("user already exists");
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }

    public UserAlreadyExistsException(User user) {
        super("user '" + user.getCpf() + "' already exists");
    }

}
