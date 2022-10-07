package com.univates.vitaldonationapi;

import com.univates.vitaldonationapi.domain.entity.Role;
import com.univates.vitaldonationapi.domain.entity.User;
import com.univates.vitaldonationapi.domain.services.RoleService;
import com.univates.vitaldonationapi.domain.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootApplication
public class VitalDonationApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VitalDonationApiApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CommandLineRunner run(UserService userService, RoleService roleService) {
		return args -> {
			var roleSuperAdmin = roleService.create(new Role("SUPER_USER"));
			var roleAdmin = roleService.create(new Role("ADMIN"));
			var roleManager = roleService.create(new Role("MANAGER"));
			var roleUser = roleService.create(new Role("USER"));

			createUser(userService, "Super Usuário", "00000000191", "vamodale", List.of(roleSuperAdmin));
			createUser(userService, "Admin", "67141053042", "vamodale", List.of(roleAdmin, roleUser));
			createUser(userService, "Manager", "95012863046", "vamodale", List.of(roleManager, roleUser));
			createUser(userService, "Usuário 1", "59210776070", "vamodale", List.of(roleUser));
			createUser(userService, "Usuário 2", "91425230016", "vamodale", List.of(roleUser));
			createUser(userService, "Usuário 3", "81482146037", "vamodale", List.of(roleUser));
		};
	}

	private static void createUser(UserService userService, String name, String cpf, String password, List<Role> roles) {
		User user = new User();
		user.setName(name);
		user.setCpf(cpf);
		user.setPassword(password);
		user.setRoles(roles);
		userService.create(user);
	}

}
