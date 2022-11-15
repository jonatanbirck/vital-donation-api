package com.univates.vitaldonationapi.app.model.schedule;

import com.univates.vitaldonationapi.domain.entity.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.time.LocalTime;
import java.util.UUID;

@Mapper(componentModel="spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ScheduleMapper {

    @Mapping(target = "hemocenter.id", source = "hemocenterId")
    Schedule map(ScheduleForm form);
    @Mapping(target = "id", source = "id")
    Schedule map(ScheduleDetail detail, UUID id);
    ScheduleSimpleDetail mapToSimpleDetail(Schedule schedule);
    ScheduleDetail mapToDetail(Schedule schedule);


    default int toInt(LocalTime time) {
        return time.toSecondOfDay();
    }

    default LocalTime toLocalTime(int time) {
        return LocalTime.ofSecondOfDay(time);
    }

}
