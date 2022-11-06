package com.univates.vitaldonationapi.app.controller;

import com.univates.vitaldonationapi.app.model.answer.AnswerDetail;
import com.univates.vitaldonationapi.app.model.answer.AnswerForm;
import com.univates.vitaldonationapi.app.model.answer.AnswerMapper;
import com.univates.vitaldonationapi.app.model.answer.AnswerSimpleDetail;
import com.univates.vitaldonationapi.domain.services.AnswerService;
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
@RequestMapping("/api/answers")
public class AnswerController {

    private final AnswerService answerService;
    private final AnswerMapper answerMapper;

    @PreAuthorize(PROFILE_USER)
    @GetMapping
    public ResponseEntity<List<AnswerDetail>> findAll() {
        return ResponseEntity.ok(answerService.findAll().stream().map(answerMapper::map).toList());
    }

    @PreAuthorize(PROFILE_USER)
    @GetMapping("/{id}")
    public ResponseEntity<AnswerDetail> findById(@PathVariable String id) {
        return ResponseEntity.ok(answerMapper.map(answerService.findById(UUID.fromString(id))))  ;
    }

    @PreAuthorize(PROFILE_MANAGER)
    @PostMapping
    public ResponseEntity<AnswerSimpleDetail> create(@Valid @RequestBody AnswerForm form) {
        return ResponseEntity.ok(answerMapper.mapToAnswerSimpleDetail(answerService.create(answerMapper.map(form))));
    }

    @PreAuthorize(PROFILE_MANAGER)
    @PutMapping("/{id}")
    public ResponseEntity<AnswerDetail> update(@PathVariable String id, @Valid @RequestBody AnswerDetail detail) {
        return ResponseEntity.ok(answerMapper.map(answerService.update(answerMapper.map(detail, id))));
    }

    @PreAuthorize(PROFILE_MANAGER)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        answerService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
