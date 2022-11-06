package com.univates.vitaldonationapi.app.controller;

import com.univates.vitaldonationapi.app.model.completed.form.CompletedFormDetail;
import com.univates.vitaldonationapi.app.model.completed.form.CompletedFormForm;
import com.univates.vitaldonationapi.app.model.completed.question.CompletedQuestionDetail;
import com.univates.vitaldonationapi.app.model.completed.question.CompletedQuestionForm;
import com.univates.vitaldonationapi.app.model.completed.question.CompletedQuestionMapper;
import com.univates.vitaldonationapi.app.model.completed.question.InCompletedQuestionDetail;
import com.univates.vitaldonationapi.domain.services.CompletedQuestionService;
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
@RequestMapping("/api/forms/completed/questions")
public class CompletedQuestionController {

    private final CompletedQuestionService completedQuestionService;
    private final CompletedQuestionMapper completedQuestionMapper;

    @PreAuthorize(PROFILE_USER)
    @GetMapping
    public ResponseEntity<List<CompletedQuestionDetail>> findAll() {
        return ResponseEntity.ok(completedQuestionService.findAll().stream()
                .map(completedQuestionMapper::map).toList());
    }

    @PreAuthorize(PROFILE_USER)
    @GetMapping("/{id}")
    public ResponseEntity<CompletedQuestionDetail> findById(@PathVariable String id) {
        return ResponseEntity.ok(completedQuestionMapper.map(completedQuestionService.findById(UUID.fromString(id))));
    }

    @PreAuthorize(PROFILE_MANAGER)
    @PostMapping
    public ResponseEntity<CompletedQuestionDetail> create(@Valid @RequestBody CompletedQuestionForm form) {
        return ResponseEntity.ok(
                completedQuestionMapper.map(completedQuestionService.create(completedQuestionMapper.map(form)))
        );
    }

    @PreAuthorize(PROFILE_MANAGER)
    @PutMapping("/{id}")
    public ResponseEntity<CompletedQuestionDetail> update(@PathVariable String id, @Valid @RequestBody InCompletedQuestionDetail detail) {
        return ResponseEntity.ok(
                completedQuestionMapper.map(completedQuestionService.update(completedQuestionMapper.map(detail, id)))
        );
    }

    @PreAuthorize(PROFILE_MANAGER)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        completedQuestionService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
