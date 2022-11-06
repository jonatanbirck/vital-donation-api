package com.univates.vitaldonationapi.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "completed_forms")
public class CompletedForm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, unique = true, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Form form;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "completedForm", targetEntity = CompletedQuestion.class, cascade = CascadeType.REMOVE)
    private Set<CompletedQuestion> completedQuestions = new HashSet<>();

}
