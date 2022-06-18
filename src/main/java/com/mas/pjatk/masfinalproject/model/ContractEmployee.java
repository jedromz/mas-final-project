package com.mas.pjatk.masfinalproject.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContractEmployee extends Employee {
    private LocalDate contractStart;
    private LocalDate contractEnd;

    @Builder
    public ContractEmployee(String firstname, String lastname, LocalDate birthDate, BigDecimal rate, BigDecimal bonus, LocalDate contractStart, LocalDate contractEnd) {
        super(firstname, lastname, birthDate, rate, bonus);
        this.contractStart = contractStart;
        this.contractEnd = contractEnd;
    }
}
