package com.univates.vitaldonationapi.app.model.answer;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Setter
@Getter
public class AnswerSimpleDetail {

    private UUID id;

    @NotBlank
    private String answer;
}
