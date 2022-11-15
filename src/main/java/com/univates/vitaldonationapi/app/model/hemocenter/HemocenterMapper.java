package com.univates.vitaldonationapi.app.model.hemocenter;

import com.univates.vitaldonationapi.domain.entity.Hemocenter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

@Mapper(componentModel="spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HemocenterMapper {

    Hemocenter map(HemocenterForm form);
    @Mapping(target = "id", source = "id")
    Hemocenter map(InHemocenterDetail detail, UUID id);
    OutHemocenterDetail mapToDetail(Hemocenter hemocenter);
    HemocenterSimpleDetail mapToSimpleDetail(Hemocenter hemocenter);

}
