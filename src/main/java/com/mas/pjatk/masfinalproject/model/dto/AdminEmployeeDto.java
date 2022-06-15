package com.mas.pjatk.masfinalproject.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
@Setter
public class AdminEmployeeDto {
    private String firstname;
    private String lastname;
    private LocalDate birthDate;
    private BigDecimal rate;
    private BigDecimal bonus;
}
