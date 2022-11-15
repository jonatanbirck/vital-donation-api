package com.univates.vitaldonationapi;

import com.univates.vitaldonationapi.domain.common.BloodType;
import com.univates.vitaldonationapi.domain.entity.*;
import com.univates.vitaldonationapi.domain.services.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
@AllArgsConstructor
public class Runner implements ApplicationRunner {

    private final UserService userService;
    private final RoleService roleService;
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final FormService formService;
    private final CompletedFormService completedFormService;
    private final CompletedQuestionService completedQuestionService;
    private final HemocenterService hemocenterService;
    private final ScheduleService scheduleService;
    private final BloodBagService bloodBagService;
    private final DonationService donationService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //create Roles
        var roleSuperAdmin = roleService.create(new Role("SUPER_USER"));
        var roleAdmin = roleService.create(new Role("ADMIN"));
        var roleManager = roleService.create(new Role("MANAGER"));
        var roleUser = roleService.create(new Role("USER"));

        //create Users
        var user1 = createUser("Super Usuário", "00000000191", "vamodale", List.of(roleSuperAdmin));
        var user2 = createUser("Admin", "67141053042", "vamodale", List.of(roleAdmin, roleUser));
        var user3 = createUser("Manager", "95012863046", "vamodale", List.of(roleManager, roleUser));
        var user4 = createUser("Usuário 1", "59210776070", "vamodale", List.of(roleUser));
        var user5 = createUser("Usuário 2", "91425230016", "vamodale", List.of(roleUser));
        var user6 = createUser("Usuário 3", "81482146037", "vamodale", List.of(roleUser));

        //create Questions
        var question01 = createQuestion("Foi usuário de drogas injetáveis?");
        var question02 = createQuestion("Teve ou tem teste positivo para HIV?");
        var question03 = createQuestion("Teve hepatite após os 10 anos de idade?");
        var question04 = createQuestion("Teve problema de coagulação de sangue?");
        var question05 = createQuestion("Foi submetido a transplante de órgãos ou de medula?");
        var question06 = createQuestion("Tem diabetes com complicações vasculares, ou ser insulino dependente?");
        var question07 = createQuestion("Teve malária?");
        var question08 = createQuestion("Teve doença de chagas?");
        var question09 = createQuestion("Teve algum tipo de câncer, incluindo leucemia?");
        var question10 = createQuestion("Teve brucelose?");
        var question11 = createQuestion("Teve hanseníase (lepra)?");
        var question12 = createQuestion("Teve calazar (leishmaniose visceral)?");
        var question13 = createQuestion("Foi submetido a gastrectomia total?");
        var question14 = createQuestion("Foi submetido a pneumectomia?");
        var question15 = createQuestion("Foi submetido a esplenectomia não decorrente de trauma?");

        var allQuestions = Set.of(
            question01,
            question02,
            question03,
            question04,
            question05,
            question06,
            question07,
            question08,
            question09,
            question10,
            question11,
            question12,
            question13,
            question14,
            question15
        );

        //create Answers
        var answer01 = createAnswer(question01, "Sim");
        var answer02 = createAnswer(question01, "Não");
        var answer03 = createAnswer(question02, "Sim");
        var answer04 = createAnswer(question02, "Não");
        var answer05 = createAnswer(question03, "Sim");
        var answer06 = createAnswer(question03, "Não");
        var answer07 = createAnswer(question04, "Sim");
        var answer08 = createAnswer(question04, "Não");
        var answer09 = createAnswer(question05, "Sim");
        var answer10 = createAnswer(question05, "Não");
        var answer11 = createAnswer(question06, "Sim");
        var answer12 = createAnswer(question06, "Não");
        var answer13 = createAnswer(question07, "Sim");
        var answer14 = createAnswer(question07, "Não");
        var answer15 = createAnswer(question08, "Sim");
        var answer16 = createAnswer(question08, "Não");
        var answer17 = createAnswer(question09, "Sim");
        var answer18 = createAnswer(question09, "Não");
        var answer19 = createAnswer(question10, "Sim");
        var answer20 = createAnswer(question10, "Não");
        var answer21 = createAnswer(question11, "Sim");
        var answer22 = createAnswer(question11, "Não");
        var answer23 = createAnswer(question12, "Sim");
        var answer24 = createAnswer(question12, "Não");
        var answer25 = createAnswer(question13, "Sim");
        var answer26 = createAnswer(question13, "Não");
        var answer27 = createAnswer(question14, "Sim");
        var answer28 = createAnswer(question14, "Não");
        var answer29 = createAnswer(question15, "Sim");
        var answer30 = createAnswer(question15, "Não");

        //create Form
        var form = createForm(allQuestions);

        //create Completed Form
        var completedForm1 = createCompletedForm(form, user1);

        //create Completed Questions
        var completedQuestion01 = createCompletedQuestion(completedForm1, question01, answer01, "observation");
        var completedQuestion02 = createCompletedQuestion(completedForm1, question02, answer04, "observation");
        var completedQuestion03 = createCompletedQuestion(completedForm1, question03, answer05, "observation");

        //create Hemocenters
        var hemocenter1 = createHemocenter(
            "Banco de Sangue Hemovale",
            "Av. Benjamin Constant, 881 - Centro, 2º. andar, Lajeado - RS, 95900-000",
            "(51) 3748-0442 / 3011-0631",
            "https://www.hemovale.com.br/site/doacao",
            "hemovale@hemovale.com.br"
        );

        var hemocenter2 = createHemocenter(
            "Hemocentro (Hemocentro do Estado do Rio Grande do Sul)",
            "Av. Bento Gonçalves, 3722 - Partenon, Porto Alegre - RS, 90650-001",
            "(51) 3336-6755 / (51) 98405-4260",
            "http://www.saude.rs.gov.br/hemocentro-do-estado-do-rio-grande-do-sul",
            "hemorgs@saude.rs.gov.br"
        );

        var hemocenter3 = createHemocenter(
            "Hemocentro Regional de Caxias Do Sul (Hemocs)",
            "R. Ernesto Alves, 2260 - Centro, Caxias do Sul - RS, 95020-360",
            "(54) 3290.4536 / 3290.4543",
            "https://saude.rs.gov.br/hemocentro-regional-de-caxias-do-sul",
            "hemocsadm@caxias.rs.gov.br"
        );

        //create Schedules
        var schedule01 = createSchedule(hemocenter1, DayOfWeek.MONDAY, LocalTime.of(7,30), LocalTime.of(13,0));
        var schedule02 = createSchedule(hemocenter1, DayOfWeek.TUESDAY, LocalTime.of(7,30), LocalTime.of(13,0));
        var schedule03 = createSchedule(hemocenter1, DayOfWeek.WEDNESDAY, LocalTime.of(7,30), LocalTime.of(13,0));
        var schedule04 = createSchedule(hemocenter1, DayOfWeek.THURSDAY, LocalTime.of(7,30), LocalTime.of(13,0));
        var schedule05 = createSchedule(hemocenter1, DayOfWeek.FRIDAY, LocalTime.of(7,30), LocalTime.of(13,0));
        var schedule06 = createSchedule(hemocenter1, DayOfWeek.SATURDAY, LocalTime.of(7,30), LocalTime.of(11,0));

        var schedule07 = createSchedule(hemocenter2, DayOfWeek.MONDAY, LocalTime.of(8,0), LocalTime.of(16,0));
        var schedule08 = createSchedule(hemocenter2, DayOfWeek.TUESDAY, LocalTime.of(8,0), LocalTime.of(16,0));
        var schedule09 = createSchedule(hemocenter2, DayOfWeek.WEDNESDAY, LocalTime.of(8,0), LocalTime.of(16,0));
        var schedule10 = createSchedule(hemocenter2, DayOfWeek.THURSDAY, LocalTime.of(8,0), LocalTime.of(16,0));
        var schedule11 = createSchedule(hemocenter2, DayOfWeek.FRIDAY, LocalTime.of(8,0), LocalTime.of(16,0));

        var schedule12 = createSchedule(hemocenter3, DayOfWeek.MONDAY, LocalTime.of(8,15), LocalTime.of(16,45));
        var schedule13 = createSchedule(hemocenter3, DayOfWeek.TUESDAY, LocalTime.of(8,15), LocalTime.of(16,45));
        var schedule14 = createSchedule(hemocenter3, DayOfWeek.WEDNESDAY, LocalTime.of(8,15), LocalTime.of(16,45));
        var schedule15 = createSchedule(hemocenter3, DayOfWeek.THURSDAY, LocalTime.of(8,15), LocalTime.of(16,45));
        var schedule16 = createSchedule(hemocenter3, DayOfWeek.FRIDAY, LocalTime.of(8,15), LocalTime.of(16,45));
        var schedule17 = createSchedule(hemocenter3, DayOfWeek.SATURDAY, LocalTime.of(8,0), LocalTime.of(12,0));

        //create BloodBags
        var bloodBag01 = createBloodBag(BloodType.ON, UUID.randomUUID().toString(), 500, hemocenter1);
        var bloodBag02 = createBloodBag(BloodType.OP, UUID.randomUUID().toString(), 500, hemocenter1);
        var bloodBag03 = createBloodBag(BloodType.ABN, UUID.randomUUID().toString(), 500, hemocenter1);
        var bloodBag04 = createBloodBag(BloodType.ABP, UUID.randomUUID().toString(), 450, hemocenter1);
        var bloodBag05 = createBloodBag(BloodType.AN, UUID.randomUUID().toString(), 500, hemocenter1);
        var bloodBag06 = createBloodBag(BloodType.AN, UUID.randomUUID().toString(), 500, hemocenter1);
        var bloodBag07 = createBloodBag(BloodType.AP, UUID.randomUUID().toString(), 500, hemocenter1);
        var bloodBag08 = createBloodBag(BloodType.OP, UUID.randomUUID().toString(), 300, hemocenter1);
        var bloodBag09 = createBloodBag(BloodType.OP, UUID.randomUUID().toString(), 450, hemocenter1);
        var bloodBag10 = createBloodBag(BloodType.BN, UUID.randomUUID().toString(), 500, hemocenter1);

        var bloodBag11 = createBloodBag(BloodType.ON, UUID.randomUUID().toString(), 500, hemocenter2);
        var bloodBag12 = createBloodBag(BloodType.OP, UUID.randomUUID().toString(), 500, hemocenter2);
        var bloodBag13 = createBloodBag(BloodType.BN, UUID.randomUUID().toString(), 500, hemocenter2);
        var bloodBag14 = createBloodBag(BloodType.OP, UUID.randomUUID().toString(), 450, hemocenter2);
        var bloodBag15 = createBloodBag(BloodType.OP, UUID.randomUUID().toString(), 500, hemocenter2);
        var bloodBag16 = createBloodBag(BloodType.AN, UUID.randomUUID().toString(), 500, hemocenter2);
        var bloodBag17 = createBloodBag(BloodType.AP, UUID.randomUUID().toString(), 500, hemocenter2);
        var bloodBag18 = createBloodBag(BloodType.OP, UUID.randomUUID().toString(), 300, hemocenter2);
        var bloodBag19 = createBloodBag(BloodType.OP, UUID.randomUUID().toString(), 450, hemocenter2);
        var bloodBag20 = createBloodBag(BloodType.BN, UUID.randomUUID().toString(), 500, hemocenter2);

        var bloodBag21 = createBloodBag(BloodType.BN, UUID.randomUUID().toString(), 500, hemocenter2);
        var bloodBag22 = createBloodBag(BloodType.BN, UUID.randomUUID().toString(), 500, hemocenter2);
        var bloodBag23 = createBloodBag(BloodType.ABN, UUID.randomUUID().toString(), 500, hemocenter2);
        var bloodBag24 = createBloodBag(BloodType.ABP, UUID.randomUUID().toString(), 450, hemocenter2);
        var bloodBag25 = createBloodBag(BloodType.AN, UUID.randomUUID().toString(), 500, hemocenter2);
        var bloodBag26 = createBloodBag(BloodType.AN, UUID.randomUUID().toString(), 500, hemocenter2);
        var bloodBag27 = createBloodBag(BloodType.OP, UUID.randomUUID().toString(), 500, hemocenter2);
        var bloodBag28 = createBloodBag(BloodType.OP, UUID.randomUUID().toString(), 300, hemocenter2);
        var bloodBag29 = createBloodBag(BloodType.OP, UUID.randomUUID().toString(), 450, hemocenter2);
        var bloodBag30 = createBloodBag(BloodType.AN, UUID.randomUUID().toString(), 500, hemocenter2);

        //create donations
        var donation01 = createDonation(user1, hemocenter1, Donation.Status.SCHEDULED);
        var donation02 = createDonation(user2, hemocenter1, Donation.Status.DONE);
        var donation03 = createDonation(user3, hemocenter1, Donation.Status.DONE);
        var donation04 = createDonation(user4, hemocenter1, Donation.Status.DONE);
        var donation05 = createDonation(user5, hemocenter1, Donation.Status.DONE);
        var donation06 = createDonation(user1, hemocenter2, Donation.Status.SCHEDULED);
        var donation07 = createDonation(user2, hemocenter2, Donation.Status.DONE);
        var donation08 = createDonation(user3, hemocenter2, Donation.Status.DONE);
        var donation09 = createDonation(user4, hemocenter2, Donation.Status.DONE);
        var donation10 = createDonation(user5, hemocenter2, Donation.Status.DONE);
        var donation11 = createDonation(user1, hemocenter3, Donation.Status.DONE);
        var donation12 = createDonation(user2, hemocenter3, Donation.Status.DONE);
        var donation13 = createDonation(user3, hemocenter3, Donation.Status.DONE);
        var donation14 = createDonation(user4, hemocenter3, Donation.Status.DONE);
        var donation15 = createDonation(user5, hemocenter3, Donation.Status.SCHEDULED);

        //create BloodBags for donations
        var bloodBag32 = createBloodBag(BloodType.OP, UUID.randomUUID().toString(), 500, hemocenter1, donation02);
        var bloodBag33 = createBloodBag(BloodType.ABN, UUID.randomUUID().toString(), 500, hemocenter1, donation03);
        var bloodBag34 = createBloodBag(BloodType.ABP, UUID.randomUUID().toString(), 450, hemocenter1, donation04);
        var bloodBag35 = createBloodBag(BloodType.AN, UUID.randomUUID().toString(), 500, hemocenter1, donation05);

        var bloodBag37 = createBloodBag(BloodType.AP, UUID.randomUUID().toString(), 500, hemocenter2, donation07);
        var bloodBag38 = createBloodBag(BloodType.OP, UUID.randomUUID().toString(), 300, hemocenter2, donation08);
        var bloodBag39 = createBloodBag(BloodType.OP, UUID.randomUUID().toString(), 450, hemocenter2, donation09);
        var bloodBag40 = createBloodBag(BloodType.BN, UUID.randomUUID().toString(), 300, hemocenter2, donation10);
        var bloodBag41 = createBloodBag(BloodType.BN, UUID.randomUUID().toString(), 300, hemocenter2, donation10);

        var bloodBag42 = createBloodBag(BloodType.OP, UUID.randomUUID().toString(), 500, hemocenter3, donation11);
        var bloodBag43 = createBloodBag(BloodType.ABN, UUID.randomUUID().toString(), 500, hemocenter3, donation12);
        var bloodBag44 = createBloodBag(BloodType.ABP, UUID.randomUUID().toString(), 450, hemocenter3, donation13);
        var bloodBag45 = createBloodBag(BloodType.AN, UUID.randomUUID().toString(), 500, hemocenter3, donation14);
    }

    private Donation createDonation(User donor, Hemocenter hemocenter, Donation.Status status) {
        var donation = new Donation();
        donation.setDonor(donor);
        donation.setSchedule(LocalDateTime.now());
        donation.setHemocenter(hemocenter);
        donation.setStatus(status);
        return donationService.create(donation);
    }

    private BloodBag createBloodBag(BloodType type, String code, int volume, Hemocenter hemocenter, Donation donation) {
        var bloodBag = new BloodBag();
        bloodBag.setBloodType(type);
        bloodBag.setCode(code);
        bloodBag.setDate(LocalDateTime.now());
        bloodBag.setVolume(volume);
        bloodBag.setHemocenter(hemocenter);
        bloodBag.setDonation(donation);
        return bloodBagService.create(bloodBag);
    }

    private BloodBag createBloodBag(BloodType type, String code, int volume, Hemocenter hemocenter) {
        var bloodBag = new BloodBag();
        bloodBag.setBloodType(type);
        bloodBag.setCode(code);
        bloodBag.setDate(LocalDateTime.now());
        bloodBag.setVolume(volume);
        bloodBag.setHemocenter(hemocenter);
        return bloodBagService.create(bloodBag);
    }

    private Hemocenter createHemocenter(String name, String address, String phone, String website, String email) {
        var hemocenter = new Hemocenter();
        hemocenter.setName(name);
        hemocenter.setAddress(address);
        hemocenter.setPhone(phone);
        hemocenter.setWebsite(website);
        hemocenter.setEmail(email);
        return hemocenterService.create(hemocenter);
    }

    private Schedule createSchedule(Hemocenter hemocenter, DayOfWeek dayOfWeek, LocalTime open, LocalTime close) {
        var schedule = new Schedule();
        schedule.setHemocenter(hemocenter);
        schedule.setDayOfWeek(dayOfWeek);
        schedule.setOpen(open);
        schedule.setClose(close);
        return scheduleService.create(schedule);
    }

    private CompletedQuestion createCompletedQuestion(CompletedForm completedForm,
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

    private CompletedForm createCompletedForm(Form form, User user) {
        var completedForm = new CompletedForm();
        completedForm.setForm(form);
        completedForm.setUser(user);
        return completedFormService.save(completedForm);
    }

    private Form createForm(Set<Question> questions) {
        var form = new Form();
        form.setQuestions(questions);
        return formService.save(form);
    }

    private Answer createAnswer(Question question, String answer) {
        var answer1 = new Answer();
        answer1.setQuestion(question);
        answer1.setAnswer(answer);
        return answerService.create(answer1);
    }

    private Question createQuestion(String question) {
        var question1 = new Question();
        question1.setQuestion(question);
        return questionService.create(question1);
    }

    private User createUser(String name, String cpf, String password, List<Role> roles) {
        User user = new User();
        user.setName(name);
        user.setCpf(cpf);
        user.setPassword(password);
        user.setRoles(roles);
        return userService.create(user);
    }

}
