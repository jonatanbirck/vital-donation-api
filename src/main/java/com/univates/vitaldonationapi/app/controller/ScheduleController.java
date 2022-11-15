package com.univates.vitaldonationapi.app.controller;

import com.univates.vitaldonationapi.app.model.schedule.ScheduleDetail;
import com.univates.vitaldonationapi.app.model.schedule.ScheduleForm;
import com.univates.vitaldonationapi.app.model.schedule.ScheduleMapper;
import com.univates.vitaldonationapi.domain.services.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static com.univates.vitaldonationapi.domain.security.UserAuthority.PROFILE_MANAGER;
import static com.univates.vitaldonationapi.domain.security.UserAuthority.PROFILE_USER;

@RestController
@AllArgsConstructor
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final ScheduleMapper scheduleMapper;

    @PreAuthorize(PROFILE_USER)
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDetail> findById(@PathVariable String id) {
        return ResponseEntity.ok(scheduleMapper.mapToDetail(scheduleService.findById(UUID.fromString(id))));
    }

    @PreAuthorize(PROFILE_USER)
    @GetMapping
    public ResponseEntity<List<ScheduleDetail>> findAll() {
        return ResponseEntity.ok(scheduleService.findAll().stream().map(scheduleMapper::mapToDetail).toList());
    }

    @PreAuthorize(PROFILE_MANAGER)
    @PostMapping
    public ResponseEntity<ScheduleDetail> create(@Valid @RequestBody ScheduleForm form) {
        return ResponseEntity.ok(scheduleMapper.mapToDetail(scheduleService.create(scheduleMapper.map(form))));
    }

    @PreAuthorize(PROFILE_MANAGER)
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleDetail> update(@PathVariable String id, @Valid @RequestBody ScheduleDetail detail) {
        return ResponseEntity.ok(scheduleMapper.mapToDetail(scheduleService.update(scheduleMapper.map(detail, UUID.fromString(id)))));
    }

    @PreAuthorize(PROFILE_MANAGER)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        scheduleService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
