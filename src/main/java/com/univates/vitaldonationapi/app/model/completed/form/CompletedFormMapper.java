package com.univates.vitaldonationapi.app.model.completed.form;

import com.univates.vitaldonationapi.domain.entity.CompletedForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel="spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompletedFormMapper {

    @Mapping(target = "form.id", source = "formId")
    @Mapping(target = "user.id", source = "userId")
    CompletedForm map(CompletedFormForm form);

    CompletedFormDetail map(CompletedForm completedForm);

    CompletedFormSimpleDetail mapToSimple(CompletedForm completedForm);

}
