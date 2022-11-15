package com.univates.vitaldonationapi.domain.entity;

import com.univates.vitaldonationapi.domain.common.BloodType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "blood_bags")
public class BloodBag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, unique = true, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Hemocenter hemocenter;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Donation donation;

    @Column(unique = true)
    private String code;

    private LocalDateTime date;

    private BloodType bloodType;

    private int volume; //ml

}
