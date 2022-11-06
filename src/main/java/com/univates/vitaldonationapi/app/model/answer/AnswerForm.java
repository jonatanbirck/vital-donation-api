package com.univates.vitaldonationapi.app.model.answer;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class AnswerForm {

    @NotBlank
    private String answer;

    @NotNull
    private UUID questionId;

}
