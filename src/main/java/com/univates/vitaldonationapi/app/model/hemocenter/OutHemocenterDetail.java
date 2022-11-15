package com.univates.vitaldonationapi.app.model.hemocenter;

import com.univates.vitaldonationapi.app.model.schedule.ScheduleSimpleDetail;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class OutHemocenterDetail {

    private UUID id;
    private String name;
    private String address;
    private String website;
    private String email;
    private String phone;
    private Set<ScheduleSimpleDetail> schedules;

}
