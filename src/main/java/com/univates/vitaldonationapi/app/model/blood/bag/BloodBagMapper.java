package com.univates.vitaldonationapi.app.model.blood.bag;

import com.univates.vitaldonationapi.domain.entity.BloodBag;
import org.mapstruct.*;

import java.util.Objects;
import java.util.UUID;

@Mapper(componentModel="spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BloodBagMapper {

    @Mapping(target = "hemocenter.id", source = "hemocenterId")
    @Mapping(target = "donation.id", source = "donationId")
    BloodBag map(BloodBagForm form);
    @Mapping(target = "id", source = "id")
    BloodBag map(BloodBagDetail detail, UUID id);
    BloodBagDetail map(BloodBag bloodBag);

    @AfterMapping
    default void afterMap(@MappingTarget BloodBag bloodBag) {
        if (Objects.nonNull(bloodBag.getDonation()) && Objects.isNull(bloodBag.getDonation().getId())) {
            bloodBag.setDonation(null);
        }
    }

}
