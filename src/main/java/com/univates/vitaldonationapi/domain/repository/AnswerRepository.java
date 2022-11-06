package com.univates.vitaldonationapi.domain.repository;

import com.univates.vitaldonationapi.domain.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AnswerRepository extends JpaRepository<Answer, UUID> {

    Optional<Answer> findByAnswerAndQuestionId(String answer, UUID questionId);

}
