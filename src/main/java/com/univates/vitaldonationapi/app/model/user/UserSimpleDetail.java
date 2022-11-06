package com.univates.vitaldonationapi.app.model.user;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserSimpleDetail {
    private UUID id;
    private String name;
}
