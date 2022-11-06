package com.univates.vitaldonationapi.app.model.question;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class QuestionForm {

    @NotBlank
    private String question;

}
