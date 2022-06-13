package com.mas.pjatk.masfinalproject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Getter
@Setter
public class Vet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vetLicense;
    private String specialization;
    @OneToMany(mappedBy = "vet")
    private Set<Visit> visits = new HashSet<>();
    @OneToOne(mappedBy = "contractEmployee")
    private Employee employee;
}
