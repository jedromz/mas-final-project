package com.mas.pjatk.masfinalproject.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Owner extends Person implements IOwner {

    private String email;
    private String mobile;
    private boolean deleted;
    @OneToMany(mappedBy = "owner")
    private Set<Patient> patients = new HashSet<>();

    public Owner(String firstname, String lastname, LocalDate birthDate, String email, String mobile) {
        super(firstname, lastname, birthDate);
        this.email = email;
        this.mobile = mobile;
    }
}
