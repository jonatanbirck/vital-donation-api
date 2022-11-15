package com.univates.vitaldonationapi.app.controller;

import com.univates.vitaldonationapi.app.model.hemocenter.HemocenterForm;
import com.univates.vitaldonationapi.app.model.hemocenter.HemocenterMapper;
import com.univates.vitaldonationapi.app.model.hemocenter.InHemocenterDetail;
import com.univates.vitaldonationapi.app.model.hemocenter.OutHemocenterDetail;
import com.univates.vitaldonationapi.domain.services.HemocenterService;
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
@RequestMapping("/api/hemocenters")
public class HemocenterController {

    private final HemocenterService hemocenterService;
    private final HemocenterMapper hemocenterMapper;

    @PreAuthorize(PROFILE_USER)
    @GetMapping("/{id}")
    public ResponseEntity<OutHemocenterDetail> findById(@PathVariable String id) {
        return ResponseEntity.ok(hemocenterMapper.mapToDetail(hemocenterService.findById(UUID.fromString(id))));
    }

    @PreAuthorize(PROFILE_USER)
    @GetMapping
    public ResponseEntity<List<OutHemocenterDetail>> findAll() {
        return ResponseEntity.ok(hemocenterService.findAll().stream().map(hemocenterMapper::mapToDetail).toList());
    }

    @PreAuthorize(PROFILE_MANAGER)
    @PostMapping
    public ResponseEntity<OutHemocenterDetail> create(@Valid @RequestBody HemocenterForm form) {
        return ResponseEntity.ok(hemocenterMapper.mapToDetail(hemocenterService.create(hemocenterMapper.map(form))));
    }

    @PreAuthorize(PROFILE_MANAGER)
    @PutMapping("/{id}")
    public ResponseEntity<OutHemocenterDetail> update(@PathVariable String id, @Valid @RequestBody InHemocenterDetail detail) {
        return ResponseEntity.ok(hemocenterMapper.mapToDetail(hemocenterService.update(hemocenterMapper.map(detail, UUID.fromString(id)))));
    }

    @PreAuthorize(PROFILE_MANAGER)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        hemocenterService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
