package com.mas.pjatk.masfinalproject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Employee extends Person {
    private BigDecimal rate;
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
