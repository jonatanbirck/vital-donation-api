package com.univates.vitaldonationapi.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, unique = true, nullable = false)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String question;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "question", targetEntity = Answer.class, cascade = CascadeType.REMOVE)
    private Collection<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "question", targetEntity = CompletedQuestion.class)
    private Collection<CompletedQuestion> completedQuestions = new ArrayList<>();

    @ManyToMany(mappedBy = "questions")
    private Set<Form> forms = new HashSet<>();

    public Question(UUID id) {
        this.id = id;
    }

}
