package com.univates.vitaldonationapi.domain.services;

import com.univates.vitaldonationapi.domain.entity.User;
import com.univates.vitaldonationapi.domain.exception.model.UserAlreadyExistsException;
import com.univates.vitaldonationapi.domain.exception.model.UserNotFoundException;
import com.univates.vitaldonationapi.domain.repository.UserRepository;
import com.univates.vitaldonationapi.helper.PropertyHelper;
import com.univates.vitaldonationapi.helper.UserAuthHelper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private RoleService roleService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public User findByName(String name) {
        return userRepository.findByName(name).orElseThrow(UserNotFoundException::new);
    }

    public User findByCpf(String cpf) {
        return userRepository.findByCpf(cpf).orElseThrow(UserNotFoundException::new);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User create(User user) {
        verifyIfExists(user.getCpf());
        if (user.getRoles().isEmpty()) {
            user.getRoles().add(roleService.findByName("USER"));
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setCreateAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public User update(User inUser) {
        var user = findById(inUser.getId());
        var cpf = user.getCpf();
        var roles = user.getRoles();
        PropertyHelper.copy(inUser, user);
        user.setCpf(cpf);
        if (Objects.nonNull(inUser.getPassword())) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        user.setUpdateAt(LocalDateTime.now());
        user.setRoles(roles);
        return userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public void addRule(UUID userId, UUID roleId) {
        var user = findById(userId);
        var role = roleService.findById(roleId);
        if (!role.getName().equals("SUPER_USER")) {
            user.getRoles().add(role);
            userRepository.save(user);
        }
    }

    public void removeRule(UUID userId, UUID roleId) {
        var user = findById(userId);
        var role = roleService.findById(roleId);
        var roles = user.getRoles();
        roles.remove(role);
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        return userRepository.findByCpf(cpf).map(UserAuthHelper::map)
                .orElseThrow(() -> new UserNotFoundException("user not found"));
    }

    private void verifyIfExists(String cpf) {
        userRepository.findByCpf(cpf).ifPresent(user -> {throw new UserAlreadyExistsException(user);});
    }

}
