package com.mas.pjatk.masfinalproject.model.command;

import com.mas.pjatk.masfinalproject.mappings.ICreateEmployeeCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class CreateFullTimeDirector implements ICreateEmployeeCommand {
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
