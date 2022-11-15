package com.univates.vitaldonationapi.app.controller;

import com.univates.vitaldonationapi.app.model.donation.DonationDetail;
import com.univates.vitaldonationapi.app.model.donation.DonationForm;
import com.univates.vitaldonationapi.app.model.donation.DonationMapper;
import com.univates.vitaldonationapi.domain.services.DonationService;
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
@RequestMapping("/api/donations")
public class DonationController {

    private final DonationService donationService;
    private final DonationMapper donationMapper;

    @PreAuthorize(PROFILE_USER)
    @GetMapping("/{id}")
    public ResponseEntity<DonationDetail> findById(@PathVariable String id) {
        return ResponseEntity.ok(donationMapper.map(donationService.findById(UUID.fromString(id))));
    }

    @PreAuthorize(PROFILE_USER)
    @GetMapping
    public ResponseEntity<List<DonationDetail>> findAll() {
        return ResponseEntity.ok(donationService.findAll().stream().map(donationMapper::map).toList());
    }

    @PreAuthorize(PROFILE_MANAGER)
    @PostMapping
    public ResponseEntity<DonationDetail> create(@Valid @RequestBody DonationForm form) {
        return ResponseEntity.ok(donationMapper.map(donationService.create(donationMapper.map(form))));
    }

    @PreAuthorize(PROFILE_MANAGER)
    @PutMapping("/{id}")
    public ResponseEntity<DonationDetail> update(@PathVariable String id, @Valid @RequestBody DonationDetail detail) {
        return ResponseEntity.ok(donationMapper.map(donationService.update(donationMapper.map(detail, UUID.fromString(id)))));
    }

    @PreAuthorize(PROFILE_MANAGER)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        donationService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
