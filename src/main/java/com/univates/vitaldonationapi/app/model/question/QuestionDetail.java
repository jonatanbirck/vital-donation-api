package com.univates.vitaldonationapi.app.model.question;

import com.univates.vitaldonationapi.app.model.answer.AnswerSimpleDetail;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class QuestionDetail {

    private UUID id;

    @NotBlank
    private String question;

    private Set<AnswerSimpleDetail> answers;

}
