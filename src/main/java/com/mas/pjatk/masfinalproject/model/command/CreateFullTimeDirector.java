package com.mas.pjatk.masfinalproject.model.command;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CreateFullTimeDirector {
    private String firstname;
    private String lastname;
    private LocalDate birthDate;

    private BigDecimal rate;
    private BigDecimal bonus;
    //director
    private LocalDate termStart;
    private LocalDate termEnd;
    //full-time
    private Integer workTime;
}
