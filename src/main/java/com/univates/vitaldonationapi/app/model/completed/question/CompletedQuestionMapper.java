package com.univates.vitaldonationapi.app.model.completed.question;

import com.univates.vitaldonationapi.domain.entity.CompletedQuestion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel="spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompletedQuestionMapper {

    @Mapping(target = "completedForm.id", source = "completedFormId")
    @Mapping(target = "question.id", source = "questionId")
    @Mapping(target = "answer.id", source = "answerId")
    CompletedQuestion map(CompletedQuestionForm form);

    @Mapping(target = "completedForm.id", source = "detail.completedFormId")
    @Mapping(target = "question.id", source = "detail.questionId")
    @Mapping(target = "answer.id", source = "detail.answerId")
    @Mapping(target = "id", source = "id")
    CompletedQuestion map(InCompletedQuestionDetail detail, String id);

    CompletedQuestionDetail map(CompletedQuestion completedQuestion);

    CompletedQuestionSimpleDetail mapToSimple(CompletedQuestion completedQuestion);

}
