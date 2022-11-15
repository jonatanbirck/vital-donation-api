package com.univates.vitaldonationapi.app.model.blood.bag;

import com.univates.vitaldonationapi.domain.common.BloodType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class BloodBagForm {

    @NotNull
    private UUID hemocenterId;

    private UUID donationId;

    @NotNull
    private String code;

    @NotNull
    private LocalDateTime date;

    @NotNull
    private BloodType bloodType;

    @NotNull
    private int volume; //ml

}
