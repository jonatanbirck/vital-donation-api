package com.univates.vitaldonationapi.domain.exception.model;

import java.util.UUID;

public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException() {
        super("already exists");
    }

    public AlreadyExistsException(String message) {
        super(message);
    }

    public AlreadyExistsException(UUID id) {
        super("already exists (" + id.toString() + ")");
    }

}
