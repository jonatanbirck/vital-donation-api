package com.univates.vitaldonationapi.app.model.completed.question;

import com.univates.vitaldonationapi.app.model.answer.AnswerSimpleDetail;
import com.univates.vitaldonationapi.app.model.question.QuestionSimpleDetail;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CompletedQuestionSimpleDetail {

    private UUID id;
    private QuestionSimpleDetail question;
    private AnswerSimpleDetail answer;
    private String observation;

}
