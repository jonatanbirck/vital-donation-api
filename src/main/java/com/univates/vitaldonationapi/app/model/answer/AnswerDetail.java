package com.univates.vitaldonationapi.app.model.answer;

import com.univates.vitaldonationapi.app.model.question.QuestionSimpleDetail;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@Setter
public class AnswerDetail {

    private UUID id;

    @NotBlank
    private String answer;

    private QuestionSimpleDetail question;

}
