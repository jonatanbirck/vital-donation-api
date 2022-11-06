package com.univates.vitaldonationapi.app.model.completed.question;

import com.univates.vitaldonationapi.app.model.answer.AnswerDetail;
import com.univates.vitaldonationapi.app.model.answer.AnswerSimpleDetail;
import com.univates.vitaldonationapi.app.model.completed.form.CompletedFormDetail;
import com.univates.vitaldonationapi.app.model.completed.form.CompletedFormSimpleDetail;
import com.univates.vitaldonationapi.app.model.question.QuestionDetail;
import com.univates.vitaldonationapi.app.model.question.QuestionSimpleDetail;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CompletedQuestionDetail {

    private UUID id;
    private CompletedFormSimpleDetail completedForm;
    private QuestionSimpleDetail question;
    private AnswerSimpleDetail answer;
    private String observation;

}
