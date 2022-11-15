package com.univates.vitaldonationapi.app.model.schedule;

import com.univates.vitaldonationapi.helper.ScheduleHelper;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
public class ScheduleSimpleDetail {

    private UUID id;

    private DayOfWeek dayOfWeek;

    private LocalTime open; //in seconds
    private LocalTime close; //in seconds

    public String getDayOfWeek() {
        return dayOfWeek.name();
    }

    public String getLabel() {
        return ScheduleHelper.toDayOfWeekPortuguese(dayOfWeek) + " " +
               ScheduleHelper.toTimeText(open) + "-" +
               ScheduleHelper.toTimeText(close);
    }

}
