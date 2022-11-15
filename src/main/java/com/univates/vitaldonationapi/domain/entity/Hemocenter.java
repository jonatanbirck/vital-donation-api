package com.univates.vitaldonationapi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "hemocenters")
public class Hemocenter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, unique = true, nullable = false)
    private UUID id;

    @Column(unique = true)
    private String name;

    private String address;

    private String website;

    private String email;

    private String phone;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "hemocenter", cascade = CascadeType.REMOVE)
    private Set<Schedule> schedules = new HashSet<>();

    public boolean isOpen() {
        var now = LocalDateTime.now();
        for (Schedule schedule : schedules) {
            if (schedule.isBetween(now)) {
                return true;
            }
        }
        return false;
    }

}
