package com.mas.pjatk.masfinalproject.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Person {
    private Long id;
    private String firstname;
    private String lastname;
    private LocalDate birthDate;

    public Integer age() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}
