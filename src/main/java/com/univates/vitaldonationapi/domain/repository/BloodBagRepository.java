package com.univates.vitaldonationapi.domain.repository;

import com.univates.vitaldonationapi.domain.entity.BloodBag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BloodBagRepository extends JpaRepository<BloodBag, UUID> {

    Optional<BloodBag> findByCode(String code);

}
