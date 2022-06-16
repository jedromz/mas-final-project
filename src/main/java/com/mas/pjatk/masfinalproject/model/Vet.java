package com.mas.pjatk.masfinalproject.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Vet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vetLicense;
    private String specialization;
    private boolean deleted;
    @OneToMany(mappedBy = "vet")
    private Set<Visit> visits = new HashSet<>();
    @OneToOne(mappedBy = "vet")
    private Employee employee;

    public Vet(String vetLicense, String specialization) {
        this.vetLicense = vetLicense;
        this.specialization = specialization;
    }
}
