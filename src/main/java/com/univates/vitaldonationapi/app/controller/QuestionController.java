package com.univates.vitaldonationapi.app.controller;

import com.univates.vitaldonationapi.app.model.question.QuestionDetail;
import com.univates.vitaldonationapi.app.model.question.QuestionForm;
import com.univates.vitaldonationapi.app.model.question.QuestionMapper;
import com.univates.vitaldonationapi.app.model.question.QuestionSimpleDetail;
import com.univates.vitaldonationapi.domain.services.QuestionService;
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
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionMapper questionMapper;

    @PreAuthorize(PROFILE_USER)
    @GetMapping
    public ResponseEntity<List<QuestionDetail>> findAll() {
        return ResponseEntity.ok(questionService.findAll().stream().map(questionMapper::map).toList());
    }

    @PreAuthorize(PROFILE_USER)
    @GetMapping("/{id}")
    public ResponseEntity<QuestionDetail> findById(@PathVariable String id) {
        return ResponseEntity.ok(questionMapper.map(questionService.findById(UUID.fromString(id))))  ;
    }

    @PreAuthorize(PROFILE_MANAGER)
    @PostMapping
    public ResponseEntity<QuestionSimpleDetail> create(@Valid @RequestBody QuestionForm form) {
        return ResponseEntity.ok(questionMapper.mapToQuestionSimpleDetail(questionService.create(questionMapper.map(form))));
    }

    @PreAuthorize(PROFILE_MANAGER)
    @PutMapping("/{id}")
    public ResponseEntity<QuestionDetail> update(@PathVariable String id, @Valid @RequestBody QuestionDetail detail) {
        return ResponseEntity.ok(questionMapper.map(questionService.update(questionMapper.map(detail, id))));
    }

    @PreAuthorize(PROFILE_MANAGER)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        questionService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
