package com.mas.pjatk.masfinalproject.model;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Employee extends Person {
    @Builder
    public Employee(String firstname, String lastname, LocalDate birthDate, BigDecimal rate, BigDecimal bonus,
                    FullTimeEmployee fullTimeEmployee, ContractEmployee contractEmployee, Vet vet, Director director,
                    AdminEmployee adminEmployee) {
        super(firstname, lastname, birthDate);
        this.rate = rate;
        this.bonus = bonus;
        this.fullTimeEmployee = fullTimeEmployee;
        this.contractEmployee = contractEmployee;
        this.vet = vet;
        this.director = director;
        this.adminEmployee = adminEmployee;
    }

    private BigDecimal rate;
    private boolean deleted;
    @ElementCollection
    private List<String> mobileNumbers = new ArrayList<>();
    @ElementCollection
    private List<String> emails = new ArrayList<>();
    private BigDecimal bonus;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "full_time_employee", referencedColumnName = "id")
    private FullTimeEmployee fullTimeEmployee;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contract_employee", referencedColumnName = "id")
    private ContractEmployee contractEmployee;
    @OneToMany(mappedBy = "employee")
    private Set<Shift> shifts = new HashSet<>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vet", referencedColumnName = "id")
    private Vet vet;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "director", referencedColumnName = "id")
    private Director director;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adminEmployee", referencedColumnName = "id")
    private AdminEmployee adminEmployee;
}
