package com.univates.vitaldonationapi.app.model.blood.bag;

import com.univates.vitaldonationapi.app.model.donation.DonationDetail;
import com.univates.vitaldonationapi.app.model.hemocenter.HemocenterSimpleDetail;
import com.univates.vitaldonationapi.domain.common.BloodType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class BloodBagDetail {

    private UUID id;
    private HemocenterSimpleDetail hemocenter;
    private DonationDetail donation;
    private String code;
    private LocalDateTime date;
    private BloodType bloodType;
    private int volume; //ml

}
