package com.univates.vitaldonationapi.app.model.completed.form;

import com.univates.vitaldonationapi.app.model.completed.question.CompletedQuestionSimpleDetail;
import com.univates.vitaldonationapi.app.model.form.FormSimpleDetail;
import com.univates.vitaldonationapi.app.model.user.UserSimpleDetail;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.UUID;

@Setter
@Getter
public class CompletedFormDetail {

    private UUID id;
    private FormSimpleDetail form;
    private UserSimpleDetail user;
    private Collection<CompletedQuestionSimpleDetail> completedQuestions;

}
