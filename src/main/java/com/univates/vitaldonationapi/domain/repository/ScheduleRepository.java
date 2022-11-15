package com.univates.vitaldonationapi.domain.repository;

import com.univates.vitaldonationapi.domain.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.UUID;

public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {

    List<Schedule> findByDayOfWeekAndHemocenterId(DayOfWeek dayOfWeek, UUID id);
}
