package com.mas.pjatk.masfinalproject.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Owner extends Person {

    private String email;
    private String mobile;
    private boolean deleted;
    @OneToMany(mappedBy = "owner")
    private Set<Patient> patients = new HashSet<>();
}
