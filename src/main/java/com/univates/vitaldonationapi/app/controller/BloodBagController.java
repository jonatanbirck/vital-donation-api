package com.univates.vitaldonationapi.app.controller;

import com.univates.vitaldonationapi.app.model.blood.bag.BloodBagDetail;
import com.univates.vitaldonationapi.app.model.blood.bag.BloodBagForm;
import com.univates.vitaldonationapi.app.model.blood.bag.BloodBagMapper;
import com.univates.vitaldonationapi.domain.services.BloodBagService;
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
@RequestMapping("/api/blood-bags")
public class BloodBagController {

    private final BloodBagService bloodBagService;
    private final BloodBagMapper bloodBagMapper;

    @PreAuthorize(PROFILE_USER)
    @GetMapping("/{id}")
    public ResponseEntity<BloodBagDetail> findById(@PathVariable String id) {
        return ResponseEntity.ok(bloodBagMapper.map(bloodBagService.findById(UUID.fromString(id))));
    }

    @PreAuthorize(PROFILE_USER)
    @GetMapping
    public ResponseEntity<List<BloodBagDetail>> findAll() {
        return ResponseEntity.ok(bloodBagService.findAll().stream().map(bloodBagMapper::map).toList());
    }

    @PreAuthorize(PROFILE_MANAGER)
    @PostMapping
    public ResponseEntity<BloodBagDetail> create(@Valid @RequestBody BloodBagForm form) {
        return ResponseEntity.ok(bloodBagMapper.map(bloodBagService.create(bloodBagMapper.map(form))));
    }

    @PreAuthorize(PROFILE_MANAGER)
    @PutMapping("/{id}")
    public ResponseEntity<BloodBagDetail> update(@PathVariable String id, @Valid @RequestBody BloodBagDetail detail) {
        return ResponseEntity.ok(bloodBagMapper.map(bloodBagService.update(bloodBagMapper.map(detail, UUID.fromString(id)))));
    }

    @PreAuthorize(PROFILE_MANAGER)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        bloodBagService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }




}
