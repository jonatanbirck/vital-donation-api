package com.univates.vitaldonationapi.domain.repository;

import com.univates.vitaldonationapi.domain.entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FormRepository extends JpaRepository<Form, UUID> {
}
