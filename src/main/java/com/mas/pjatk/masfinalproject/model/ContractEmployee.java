package com.mas.pjatk.masfinalproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContractEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate contractStart;
    private LocalDate contractEnd;
    @OneToOne(mappedBy = "contractEmployee")
    private Employee employee;

    public ContractEmployee(LocalDate contractStart, LocalDate contractEnd) {
        this.contractStart = contractStart;
        this.contractEnd = contractEnd;
    }
}
