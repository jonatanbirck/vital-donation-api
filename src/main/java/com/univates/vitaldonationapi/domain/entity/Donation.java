package com.univates.vitaldonationapi.domain.entity;

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

    @Column(nullable = false)
    protected LocalDateTime createAt;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    protected User createBy;

    @Column
    protected LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    protected User updateBy;

}
