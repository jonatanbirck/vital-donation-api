package com.univates.vitaldonationapi.app.controller;

import com.univates.vitaldonationapi.app.model.role.RoleForm;
import com.univates.vitaldonationapi.app.model.role.RoleMapper;
import com.univates.vitaldonationapi.domain.entity.Role;
import com.univates.vitaldonationapi.domain.services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static com.univates.vitaldonationapi.domain.security.UserAuthority.PROFILE_SUPER_USER;

@RestController
@AllArgsConstructor
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService service;

    @GetMapping("/{id}")
    public ResponseEntity<Role> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(UUID.fromString(id)));
    }

    @GetMapping
    public ResponseEntity<List<Role>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PreAuthorize(PROFILE_SUPER_USER)
    @PostMapping
    public ResponseEntity<Role> create(@Valid @RequestBody RoleForm form) {
        var role = RoleMapper.map(form);
        return ResponseEntity.ok(service.create(role));
    }

    @PreAuthorize(PROFILE_SUPER_USER)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        var role = service.findById(UUID.fromString(id));
        service.delete(role);
        return ResponseEntity.noContent().build();
    }

}
