package com.univates.vitaldonationapi.app.model.schedule;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
public class ScheduleForm {

    @NotNull
    private DayOfWeek dayOfWeek;
    @NotNull
    private UUID hemocenterId;
    @NotNull
    private LocalTime open; //in seconds
    @NotNull
    private LocalTime close; //in seconds

}
