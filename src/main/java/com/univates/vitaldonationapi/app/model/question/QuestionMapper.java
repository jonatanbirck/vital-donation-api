package com.univates.vitaldonationapi.app.model.question;

import com.univates.vitaldonationapi.domain.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel="spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuestionMapper {

    Question map(QuestionForm form);

    @Mapping(target = "id", source = "id")
    Question map(QuestionDetail detail, String id);

    QuestionDetail map(Question user);

    QuestionSimpleDetail mapToQuestionSimpleDetail(Question user);

}
