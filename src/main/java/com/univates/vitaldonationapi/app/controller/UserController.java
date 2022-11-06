package com.univates.vitaldonationapi.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.univates.vitaldonationapi.app.model.user.UserDetail;
import com.univates.vitaldonationapi.app.model.user.UserForm;
import com.univates.vitaldonationapi.app.model.user.UserMapper;
import com.univates.vitaldonationapi.domain.security.TokenManager;
import com.univates.vitaldonationapi.domain.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.univates.vitaldonationapi.domain.security.UserAuthority.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;
    private final UserMapper userMapper;

    @PreAuthorize(PROFILE_MANAGER)
    @GetMapping("/{id}")
    public ResponseEntity<UserDetail> findById(@PathVariable String id) {
        return ResponseEntity.ok(userMapper.map(service.findById(UUID.fromString(id))));
    }

    @PreAuthorize(PROFILE_USER)
    @GetMapping
    public ResponseEntity<UserDetail> findByUserLogged(@AuthenticationPrincipal String cpf) {
        return ResponseEntity.ok(userMapper.map(service.findByCpf(cpf)));
    }

    @PreAuthorize(PROFILE_SUPER_USER)
    @GetMapping("/list")
    public ResponseEntity<List<UserDetail>> findAll() {
        return ResponseEntity.ok(service.findAll().stream().map(userMapper::map).toList());
    }

    @PostMapping
    public ResponseEntity<UserDetail> create(@Valid @RequestBody UserForm form) {
        var user = userMapper.map(form);
        return ResponseEntity.ok(userMapper.map(service.create(user)));
    }

    @PreAuthorize(PROFILE_USER)
    @PutMapping("/{id}")
    public ResponseEntity<UserDetail> update(@Valid @RequestBody UserDetail detail, @PathVariable String id) {
        var user = userMapper.map(detail, id);
        return ResponseEntity.ok(userMapper.map(service.update(user)));
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

    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), TokenManager.refreshToken(request, service));
        } catch (Exception e) {
            response.setHeader("error", e.getMessage());
            response.setStatus(HttpStatus.FORBIDDEN.value());

            Map<String,String> error = new HashMap<>();
            error.put("error_message", e.getMessage());

            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        }
    }

}
