package com.univates.vitaldonationapi.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "donations")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, unique = true, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private LocalDateTime schedule;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private User donor;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Hemocenter hemocenter;

    private Status status = Status.SCHEDULED;

    public enum Status {
        @JsonProperty("Agendado")
        SCHEDULED,
        @JsonProperty("Cancelado")
        CANCELED,
        @JsonProperty("Realizado")
        DONE
    }

}
