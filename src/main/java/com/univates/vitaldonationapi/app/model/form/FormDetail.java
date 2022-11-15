package com.univates.vitaldonationapi.app.model.form;

import com.univates.vitaldonationapi.app.model.question.QuestionDetail;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Setter
@Getter
public class FormDetail {

    private UUID id;
    private String name;
    private Set<QuestionDetail> questions;

}
