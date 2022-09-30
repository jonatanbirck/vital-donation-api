package com.univates.vitaldonationapi.app.controller;

import com.univates.vitaldonationapi.app.model.user.UserDetail;
import com.univates.vitaldonationapi.app.model.user.UserForm;
import com.univates.vitaldonationapi.app.model.user.UserMapper;
import com.univates.vitaldonationapi.domain.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static com.univates.vitaldonationapi.domain.security.UserAuthority.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    @PreAuthorize(PROFILE_MANAGER)
    @GetMapping("/{id}")
    public ResponseEntity<UserDetail> findById(@PathVariable String id) {
        return ResponseEntity.ok(UserMapper.map(service.findById(UUID.fromString(id))));
    }

    @PreAuthorize(PROFILE_SUPER_USER)
    @GetMapping
    public ResponseEntity<List<UserDetail>> findAll() {
        return ResponseEntity.ok(service.findAll().stream().map(UserMapper::map).toList());
    }

    @PostMapping
    public ResponseEntity<UserDetail> create(@Valid @RequestBody UserForm form) {
        var user = UserMapper.map(form);
        return ResponseEntity.ok(UserMapper.map(service.create(user)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDetail> update(@Valid @RequestBody UserDetail detail, @PathVariable String id) {
        var user = UserMapper.map(detail, id);
        return ResponseEntity.ok(UserMapper.map(service.update(user)));
    }

    @PreAuthorize(PROFILE_SUPER_USER)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        var user = service.findById(UUID.fromString(id));
        service.delete(user);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize(PROFILE_ADMIN)
    @PutMapping("/add-role/{id}")
    public ResponseEntity<Void> addRule(@PathVariable String id, @RequestParam String roleId) {
        service.addRule(UUID.fromString(id), UUID.fromString(roleId));
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize(PROFILE_ADMIN)
    @PutMapping("/remove-role/{id}")
    public ResponseEntity<Void> removeRule(@PathVariable String id, @RequestParam String roleId) {
        service.removeRule(UUID.fromString(id), UUID.fromString(roleId));
        return ResponseEntity.noContent().build();
    }

}
