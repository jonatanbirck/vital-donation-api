package com.univates.vitaldonationapi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "schedules")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, unique = true, nullable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Hemocenter hemocenter;

    private LocalTime open;
    private LocalTime close;

    public boolean isBetween(LocalTime time) {
        return time.equals(open) || time.equals(close) || (time.isAfter(open) && time.isBefore(close));
    }

    public boolean isBetween(LocalDateTime dateTime) {
        return dateTime.getDayOfWeek().equals(dayOfWeek) && isBetween(dateTime.toLocalTime());
    }

    public boolean isToday() {
        return LocalDateTime.now().getDayOfWeek().equals(dayOfWeek);
    }

}
