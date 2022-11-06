package com.univates.vitaldonationapi.app.controller;

import com.univates.vitaldonationapi.app.model.form.*;
import com.univates.vitaldonationapi.domain.services.FormService;
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
@RequestMapping("/api/forms")
public class FormController {

    private final FormService formService;
    private final FormMapper formMapper;

    @PreAuthorize(PROFILE_USER)
    @GetMapping
    public ResponseEntity<List<FormDetail>> findAll() {
        return ResponseEntity.ok(formService.findAll().stream().map(formMapper::map).toList());
    }

    @PreAuthorize(PROFILE_USER)
    @GetMapping("/{id}")
    public ResponseEntity<FormDetail> findById(@PathVariable String id) {
        return ResponseEntity.ok(formMapper.map(formService.findById(UUID.fromString(id))))  ;
    }

    @PreAuthorize(PROFILE_MANAGER)
    @PostMapping
    public ResponseEntity<FormSimpleDetail> create(@Valid @RequestBody FormForm form) {
        return ResponseEntity.ok(formMapper.mapToFormSimpleDetail(formService.save(formMapper.map(form))));
    }

    @PreAuthorize(PROFILE_MANAGER)
    @PutMapping("/{id}")
    public ResponseEntity<FormDetail> update(@PathVariable String id, @Valid @RequestBody InFormDetail detail) {
        return ResponseEntity.ok(formMapper.map(formService.save(formMapper.map(detail, id))));
    }

    @PreAuthorize(PROFILE_MANAGER)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        formService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
