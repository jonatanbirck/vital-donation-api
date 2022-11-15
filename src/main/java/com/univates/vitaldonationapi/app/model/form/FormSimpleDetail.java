package com.univates.vitaldonationapi.app.model.form;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class FormSimpleDetail {
    private UUID id;
    private String name;
}
