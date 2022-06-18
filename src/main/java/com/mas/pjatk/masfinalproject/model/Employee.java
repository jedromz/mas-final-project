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
public abstract class Employee extends Person {
    private BigDecimal rate;
    private boolean deleted;
    private BigDecimal bonus;
    @ElementCollection
    private List<String> mobileNumbers = new ArrayList<>();
    @ElementCollection
    private List<String> emails = new ArrayList<>();
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

    public Employee(String firstname, String lastname, LocalDate birthDate, BigDecimal rate, BigDecimal bonus) {
        super(firstname, lastname, birthDate);
        this.rate = rate;
        this.bonus = bonus;
    }

}
