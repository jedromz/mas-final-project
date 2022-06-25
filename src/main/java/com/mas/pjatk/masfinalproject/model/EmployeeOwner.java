package com.mas.pjatk.masfinalproject.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class EmployeeOwner extends Employee implements IOwner {

    @OneToMany(mappedBy = "owner")
    private Set<Patient> patients = new HashSet<>();

    @Override
    public String getEmail() {
        return getEmails().get(0);
    }

    @Override
    public String getMobile() {
        return getMobileNumbers().get(0);
    }

    @Override
    public Set<Patient> getPatients() {
        return patients;
    }
}
