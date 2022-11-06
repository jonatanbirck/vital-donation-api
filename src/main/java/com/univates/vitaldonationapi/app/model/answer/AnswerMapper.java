package com.univates.vitaldonationapi.app.model.answer;

import com.univates.vitaldonationapi.domain.entity.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

@Mapper(componentModel="spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnswerMapper {

    @Mapping(target = "question.id", source = "questionId")
    Answer map(AnswerForm form);

    @Mapping(target = "id", source = "id")
    Answer map(AnswerDetail detail, String id);

    AnswerDetail map(Answer user);

    AnswerSimpleDetail mapToAnswerSimpleDetail(Answer user);

    default UUID toUUID(String uuid) {
        return UUID.fromString(uuid);
    }

}
