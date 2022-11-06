package com.univates.vitaldonationapi.app.model.form;

import com.univates.vitaldonationapi.domain.entity.Form;
import com.univates.vitaldonationapi.domain.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel="spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FormMapper {

    @Mapping(target = "questions", expression = "java(toQuestions(form.getQuestionIds()))")
    Form map(FormForm form);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "questions", expression = "java(toQuestions(detail.getQuestionIds()))")
    Form map(InFormDetail detail, String id);

    FormDetail map(Form form);

    FormSimpleDetail mapToFormSimpleDetail(Form form);

    default Set<Question> toQuestions(Set<UUID> questionIds) {
        return questionIds.stream().map(Question::new).collect(Collectors.toSet());
    }
}
