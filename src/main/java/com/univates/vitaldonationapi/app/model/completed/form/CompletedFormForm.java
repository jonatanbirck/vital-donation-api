package com.univates.vitaldonationapi.app.model.completed.form;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CompletedFormForm {

    private UUID formId;
    private UUID userId;

}
