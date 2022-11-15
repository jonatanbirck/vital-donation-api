package com.univates.vitaldonationapi.domain.repository;

import com.univates.vitaldonationapi.domain.entity.Hemocenter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface HemocenterRepository extends JpaRepository<Hemocenter, UUID> {

    Optional<Hemocenter> findByName(String name);

}
