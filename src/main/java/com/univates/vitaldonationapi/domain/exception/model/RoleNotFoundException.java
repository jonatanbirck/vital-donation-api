package com.univates.vitaldonationapi.domain.exception.model;

public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException() {
        super("role not found");
    }

    public RoleNotFoundException(String message) {
        super(message);
    }

}