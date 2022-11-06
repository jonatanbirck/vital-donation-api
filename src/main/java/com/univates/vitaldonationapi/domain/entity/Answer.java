package com.univates.vitaldonationapi.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, unique = true, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Question question;

    @Column(nullable = false)
    private String answer;

    @OneToMany(mappedBy = "question", targetEntity = CompletedQuestion.class)
    private Collection<CompletedQuestion> completedQuestions = new ArrayList<>();

}
