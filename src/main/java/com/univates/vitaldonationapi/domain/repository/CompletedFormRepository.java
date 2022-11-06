package com.univates.vitaldonationapi.domain.repository;

import com.univates.vitaldonationapi.domain.entity.CompletedForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompletedFormRepository extends JpaRepository<CompletedForm, UUID> {
}
