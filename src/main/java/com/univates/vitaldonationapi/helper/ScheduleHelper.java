package com.univates.vitaldonationapi.helper;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ScheduleHelper {

    private ScheduleHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static String toDayOfWeekPortuguese(DayOfWeek dayOfWeek) {
        return switch (dayOfWeek) {
            case MONDAY -> "Segunda-feira";
            case TUESDAY -> "Terça-feira";
            case WEDNESDAY -> "Quarta-feira";
            case THURSDAY -> "Quinta-feira";
            case FRIDAY -> "Sexta-feira";
            case SATURDAY -> "Sábado";
            case SUNDAY -> "Domingo";
        };
    }

    public static String toTimeText(LocalTime time) {
        return time.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

}
