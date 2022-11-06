package com.univates.vitaldonationapi.app.model.completed.question;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class InCompletedQuestionDetail {

    private UUID id;
    private UUID completedFormId;
    private UUID questionId;
    private UUID answerId;
    private String observation;

}
