package com.univates.vitaldonationapi.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "forms")
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, unique = true, nullable = false)
    private UUID id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "forms_questions",
        joinColumns = @JoinColumn(name = "forms_id"),
        inverseJoinColumns = @JoinColumn(name = "questions_id"))
    private Set<Question> questions = new HashSet<>();

}
