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

			userService.create(
				new User()
					.withName("Super Usuário")
					.withCpf("00000000191")
					.withPassword("vamodale")
					.withRoles(List.of(roleSuperAdmin))
			);

			userService.create(
				new User()
					.withName("Admin")
					.withCpf("67141053042")
					.withPassword("vamodale")
					.withRoles(List.of(roleAdmin, roleUser))
			);

			userService.create(
				new User()
					.withName("Manager")
					.withCpf("95012863046")
					.withPassword("vamodale")
					.withRoles(List.of(roleManager, roleUser))
			);

			userService.create(
				new User()
					.withName("Usuário 1")
					.withCpf("59210776070")
					.withPassword("vamodale")
					.withRoles(List.of(roleUser))
			);

			userService.create(
				new User()
					.withName("Usuário 2")
					.withCpf("91425230016")
					.withPassword("vamodale")
					.withRoles(List.of(roleUser))
			);

			userService.create(
				new User()
					.withName("Usuário 3")
					.withCpf("81482146037")
					.withPassword("vamodale")
					.withRoles(List.of(roleUser))
			);
		};
	}

}
