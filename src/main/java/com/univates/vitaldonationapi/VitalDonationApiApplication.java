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
								 CompletedFormService completedFormService,
								 CompletedQuestionService completedQuestionService) {
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
			var answer01 = createAnswer(answerService, question01, "Sim");
			var answer02 = createAnswer(answerService, question01, "Não");
			var answer03 = createAnswer(answerService, question02, "Sim");
			var answer04 = createAnswer(answerService, question02, "Não");
			var answer05 = createAnswer(answerService, question03, "Sim");
			var answer06 = createAnswer(answerService, question03, "Não");
			var answer07 = createAnswer(answerService, question04, "Sim");
			var answer08 = createAnswer(answerService, question04, "Não");
			var answer09 = createAnswer(answerService, question05, "Sim");
			var answer10 = createAnswer(answerService, question05, "Não");
			var answer11 = createAnswer(answerService, question06, "Sim");
			var answer12 = createAnswer(answerService, question06, "Não");
			var answer13 = createAnswer(answerService, question07, "Sim");
			var answer14 = createAnswer(answerService, question07, "Não");
			var answer15 = createAnswer(answerService, question08, "Sim");
			var answer16 = createAnswer(answerService, question08, "Não");
			var answer17 = createAnswer(answerService, question09, "Sim");
			var answer18 = createAnswer(answerService, question09, "Não");
			var answer19 = createAnswer(answerService, question10, "Sim");
			var answer20 = createAnswer(answerService, question10, "Não");
			var answer21 = createAnswer(answerService, question11, "Sim");
			var answer22 = createAnswer(answerService, question11, "Não");
			var answer23 = createAnswer(answerService, question12, "Sim");
			var answer24 = createAnswer(answerService, question12, "Não");
			var answer25 = createAnswer(answerService, question13, "Sim");
			var answer26 = createAnswer(answerService, question13, "Não");
			var answer27 = createAnswer(answerService, question14, "Sim");
			var answer28 = createAnswer(answerService, question14, "Não");
			var answer29 = createAnswer(answerService, question15, "Sim");
			var answer30 = createAnswer(answerService, question15, "Não");

			//create Form
			var form = createForm(formService, allQuestions);

			//create Completed Form
			var completedForm1 = createCompletedForm(completedFormService, form, user1);

			//create Completed Questions
			var completedQuestion01 = createCompletedQuestion(completedQuestionService, completedForm1, question01, answer01, "observation");
			var completedQuestion02 = createCompletedQuestion(completedQuestionService, completedForm1, question02, answer04, "observation");
			var completedQuestion03 = createCompletedQuestion(completedQuestionService, completedForm1, question03, answer05, "observation");
		};
	}

	private static CompletedQuestion createCompletedQuestion(CompletedQuestionService completedQuestionService,
															 CompletedForm completedForm,
															 Question question,
															 Answer answer,
															 String observation) {
		var completedQuestion = new CompletedQuestion();
		completedQuestion.setCompletedForm(completedForm);
		completedQuestion.setQuestion(question);
		completedQuestion.setAnswer(answer);
		completedQuestion.setObservation(observation);
		return completedQuestionService.create(completedQuestion);
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
