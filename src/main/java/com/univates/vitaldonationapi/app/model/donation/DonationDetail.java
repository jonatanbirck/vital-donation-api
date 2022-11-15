package com.univates.vitaldonationapi.app.model.donation;

import com.univates.vitaldonationapi.app.model.hemocenter.HemocenterSimpleDetail;
import com.univates.vitaldonationapi.app.model.user.UserSimpleDetail;
import com.univates.vitaldonationapi.domain.entity.Donation;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class DonationDetail {

    private UUID id;
    private LocalDateTime schedule;
    private UserSimpleDetail donor;
    private HemocenterSimpleDetail hemocenter;
    private Donation.Status status;

}
