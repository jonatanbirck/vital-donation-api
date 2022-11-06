package com.univates.vitaldonationapi.app.model.completed.question;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CompletedQuestionForm {

    private UUID completedFormId;
    private UUID questionId;
    private UUID answerId;
    private String observation;

}
