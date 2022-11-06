package com.univates.vitaldonationapi.app.model.question;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Setter
@Getter
public class QuestionSimpleDetail {

    private UUID id;

    @NotBlank
    private String question;

}
