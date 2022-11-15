package com.univates.vitaldonationapi.app.model.donation;

import com.univates.vitaldonationapi.domain.entity.Donation;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class DonationForm {

    @NotNull
    private LocalDateTime schedule;
    @NotNull
    private UUID donorId;
    @NotNull
    private UUID hemocenterId;
    private Donation.Status status = Donation.Status.SCHEDULED;

}
