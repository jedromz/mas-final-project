package com.mas.pjatk.masfinalproject.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate birthDate;
    private String type;
    private String race;
    private boolean deleted;
    @OneToMany(mappedBy = "patient")
    private Set<Visit> visits = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;

    public Patient(String name, LocalDate birthDate, String type, String race) {
        this.name = name;
        this.birthDate = birthDate;
        this.type = type;
        this.race = race;
    }

    public Integer age() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}
