package com.univates.vitaldonationapi.domain.repository;

import com.univates.vitaldonationapi.domain.entity.CompletedQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompletedQuestionRepository extends JpaRepository<CompletedQuestion, UUID> {

    Optional<CompletedQuestion> findByQuestionIdAndCompletedFormId(UUID questionId, UUID completedFormId);
}
