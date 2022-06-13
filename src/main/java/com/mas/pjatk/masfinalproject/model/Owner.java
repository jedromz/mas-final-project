package com.mas.pjatk.masfinalproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Owner extends Person {

    private String email;
    private String mobile;
    @OneToMany(mappedBy = "owner")
    private Set<Patient> patients = new HashSet<>();
}
