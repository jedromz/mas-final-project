package com.mas.pjatk.masfinalproject.model.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class CreateContractAdminEmployeeCommand implements ICreateContractEmployee {
    private String firstname;
    private String lastname;
    private LocalDate birthDate;
    private BigDecimal rate;
    private BigDecimal bonus;
    private LocalDate contractStart;
    private LocalDate contractEnd;
}
