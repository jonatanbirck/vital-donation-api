package com.univates.vitaldonationapi.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "completed_quentions")
public class CompletedQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, unique = true, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private CompletedForm completedForm;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Question question;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Answer answer;

    @Column
    private String observation;

}
