package com.mas.pjatk.masfinalproject.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FullTimeEmployee extends Employee {

    private Integer workTime;
    @OneToMany(mappedBy = "fullTimeEmployee")
    private Set<Vacation> vacations = new HashSet<>();

    @Builder
    public FullTimeEmployee(String firstname, String lastname, LocalDate birthDate, BigDecimal rate, BigDecimal bonus, Integer workTime) {
        super(firstname, lastname, birthDate, rate, bonus);
        this.workTime = workTime;
    }
}
