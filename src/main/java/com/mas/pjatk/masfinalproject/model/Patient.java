package com.mas.pjatk.masfinalproject.model;

import java.time.LocalDate;
import java.time.Period;

public class Patient {
    private String name;
    private LocalDate birthDate;
    private String type;
    private String race;

    public Integer age() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}
