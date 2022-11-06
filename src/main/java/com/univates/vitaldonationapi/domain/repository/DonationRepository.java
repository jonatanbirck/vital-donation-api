package com.univates.vitaldonationapi.domain.repository;

import com.univates.vitaldonationapi.domain.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DonationRepository extends JpaRepository<Donation, UUID> {
}
