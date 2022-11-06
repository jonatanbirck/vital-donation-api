package com.univates.vitaldonationapi;

import com.univates.vitaldonationapi.domain.entity.*;
import com.univates.vitaldonationapi.domain.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Set;

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
	public CommandLineRunner run(UserService userService,
								 RoleService roleService,
								 QuestionService questionService,
								 AnswerService answerService,
								 FormService formService,
								 CompletedFormService completedFormService) {
		return args -> {
			//create Roles
			var roleSuperAdmin = roleService.create(new Role("SUPER_USER"));
			var roleAdmin = roleService.create(new Role("ADMIN"));
			var roleManager = roleService.create(new Role("MANAGER"));
			var roleUser = roleService.create(new Role("USER"));

			//create Users
			var user1 = createUser(userService, "Super Usuário", "00000000191", "vamodale", List.of(roleSuperAdmin));
			var user2 = createUser(userService, "Admin", "67141053042", "vamodale", List.of(roleAdmin, roleUser));
			var user3 = createUser(userService, "Manager", "95012863046", "vamodale", List.of(roleManager, roleUser));
			var user4 = createUser(userService, "Usuário 1", "59210776070", "vamodale", List.of(roleUser));
			var user5 = createUser(userService, "Usuário 2", "91425230016", "vamodale", List.of(roleUser));
			var user6 = createUser(userService, "Usuário 3", "81482146037", "vamodale", List.of(roleUser));

			//create Questions
			var question01 = createQuestion(questionService, "Foi usuário de drogas injetáveis?");
			var question02 = createQuestion(questionService, "Teve ou tem teste positivo para HIV?");
			var question03 = createQuestion(questionService, "Teve hepatite após os 10 anos de idade?");
			var question04 = createQuestion(questionService, "Teve problema de coagulação de sangue?");
			var question05 = createQuestion(questionService, "Foi submetido a transplante de órgãos ou de medula?");
			var question06 = createQuestion(questionService, "Tem diabetes com complicações vasculares, ou ser insulino dependente?");
			var question07 = createQuestion(questionService, "Teve malária?");
			var question08 = createQuestion(questionService, "Teve doença de chagas?");
			var question09 = createQuestion(questionService, "Teve algum tipo de câncer, incluindo leucemia?");
			var question10 = createQuestion(questionService, "Teve brucelose?");
			var question11 = createQuestion(questionService, "Teve hanseníase (lepra)?");
			var question12 = createQuestion(questionService, "Teve calazar (leishmaniose visceral)?");
			var question13 = createQuestion(questionService, "Foi submetido a gastrectomia total?");
			var question14 = createQuestion(questionService, "Foi submetido a pneumectomia?");
			var question15 = createQuestion(questionService, "Foi submetido a esplenectomia não decorrente de trauma?");

			var allQuestions = Set.of(question01, question02, question03, question04, question05, question06, question07, question08, question09, question10, question11, question12, question13, question14, question15);

			//create Answers
			allQuestions.forEach(question -> createAnswer(answerService, question, "Sim"));
			allQuestions.forEach(question -> createAnswer(answerService, question, "Não"));

			//create Form
			var form = createForm(formService, allQuestions);

			//create Completed Form
			var completedForm = createCompletedForm(completedFormService, form, user1);
		};
	}

	private static CompletedForm createCompletedForm(CompletedFormService completedFormService, Form form, User user) {
		var completedForm = new CompletedForm();
		completedForm.setForm(form);
		completedForm.setUser(user);
		return completedFormService.save(completedForm);
	}

	private static Form createForm(FormService formService, Set<Question> questions) {
		var form = new Form();
		form.setQuestions(questions);
		return formService.save(form);
	}

	private static Answer createAnswer(AnswerService answerService, Question question, String answer) {
		var answer1 = new Answer();
		answer1.setQuestion(question);
		answer1.setAnswer(answer);
		return answerService.create(answer1);
	}

	private static Question createQuestion(QuestionService questionService, String question) {
		var question1 = new Question();
		question1.setQuestion(question);
		return questionService.create(question1);
	}

	private static User createUser(UserService userService, String name, String cpf, String password, List<Role> roles) {
		User user = new User();
		user.setName(name);
		user.setCpf(cpf);
		user.setPassword(password);
		user.setRoles(roles);
		return userService.create(user);
	}

}
