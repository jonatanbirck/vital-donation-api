package com.univates.vitaldonationapi.app.model.donation;

import com.univates.vitaldonationapi.domain.entity.Donation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

@Mapper(componentModel="spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DonationMapper {

    @Mapping(target = "donor.id", source = "donorId")
    @Mapping(target = "hemocenter.id", source = "hemocenterId")
    Donation map(DonationForm form);
    @Mapping(target = "id", source = "id")
    Donation map(DonationDetail detail, UUID id);
    DonationDetail map(Donation hemocenter);

}
