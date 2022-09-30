package com.univates.vitaldonationapi.domain.repository;

import com.univates.vitaldonationapi.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByCpf(String cpf);

    Optional<User> findByName(String name);

}
