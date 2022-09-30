package com.univates.vitaldonationapi.domain.services;

import com.univates.vitaldonationapi.domain.entity.Role;
import com.univates.vitaldonationapi.domain.exception.model.RoleNotFoundException;
import com.univates.vitaldonationapi.domain.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class RoleService {

    private RoleRepository roleRepository;

    public Role findById(UUID id) {
        return roleRepository.findById(id).orElseThrow(RoleNotFoundException::new);
    }

    public Role findByName(String name) {
        return roleRepository.findByName(name).orElseThrow(RoleNotFoundException::new);
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role create(Role role) {
        return roleRepository.save(role);
    }

    public void delete(Role role) {
        if (!role.getName().equals("SUPER_USER") ||
            !role.getName().equals("ADMIN") ||
            !role.getName().equals("MANAGER") ||
            !role.getName().equals("USER")) {
            roleRepository.delete(role);
        }
    }

}
