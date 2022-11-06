package com.univates.vitaldonationapi.app.controller;

import com.univates.vitaldonationapi.app.model.completed.form.CompletedFormDetail;
import com.univates.vitaldonationapi.app.model.completed.form.CompletedFormForm;
import com.univates.vitaldonationapi.app.model.completed.form.CompletedFormMapper;
import com.univates.vitaldonationapi.app.model.form.FormDetail;
import com.univates.vitaldonationapi.app.model.form.FormForm;
import com.univates.vitaldonationapi.app.model.form.FormSimpleDetail;
import com.univates.vitaldonationapi.app.model.form.InFormDetail;
import com.univates.vitaldonationapi.domain.services.CompletedFormService;
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
@RequestMapping("/api/forms/completed/forms")
public class CompletedFormController {

    private final CompletedFormService completedFormService;
    private final CompletedFormMapper completedFormMapper;

    @PreAuthorize(PROFILE_USER)
    @GetMapping
    public ResponseEntity<List<CompletedFormDetail>> findAll() {
        return ResponseEntity.ok(completedFormService.findAll().stream().map(completedFormMapper::map).toList());
    }

    @PreAuthorize(PROFILE_USER)
    @GetMapping("/{id}")
    public ResponseEntity<CompletedFormDetail> findById(@PathVariable String id) {
        return ResponseEntity.ok(completedFormMapper.map(completedFormService.findById(UUID.fromString(id))))  ;
    }

    @PreAuthorize(PROFILE_MANAGER)
    @PostMapping
    public ResponseEntity<CompletedFormDetail> create(@Valid @RequestBody CompletedFormForm form) {
        return ResponseEntity.ok(completedFormMapper.map(completedFormService.save(completedFormMapper.map(form))));
    }

    @PreAuthorize(PROFILE_MANAGER)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        completedFormService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
