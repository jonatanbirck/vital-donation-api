package com.univates.vitaldonationapi.domain.services;

import com.univates.vitaldonationapi.domain.entity.Schedule;
import com.univates.vitaldonationapi.domain.exception.model.AlreadyExistsException;
import com.univates.vitaldonationapi.domain.exception.model.NotFoundException;
import com.univates.vitaldonationapi.domain.repository.ScheduleRepository;
import com.univates.vitaldonationapi.helper.PropertyHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public Schedule findById(UUID id) {
        return scheduleRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    public Schedule create(Schedule schedule) {
        verifyIfExists(schedule);
        return scheduleRepository.save(schedule);
    }

    public Schedule update(Schedule inSchedule) {
        var schedule = findById(inSchedule.getId());
        PropertyHelper.copy(inSchedule, schedule);
        return scheduleRepository.save(schedule);
    }

    public void delete(UUID id) {
        scheduleRepository.delete(findById(id));
    }

    public void delete(Schedule schedule) {
        scheduleRepository.delete(schedule);
    }

    private void verifyIfExists(Schedule inSchedule) {
        List<Schedule> schedules = scheduleRepository.findByDayOfWeekAndHemocenterId(
            inSchedule.getDayOfWeek(),
            inSchedule.getHemocenter().getId()
        );
        schedules.forEach(schedule -> {
            if (schedule.isBetween(inSchedule.getOpen()) || schedule.isBetween(inSchedule.getClose())) {
                throw new AlreadyExistsException();
            }
        });
    }

}
