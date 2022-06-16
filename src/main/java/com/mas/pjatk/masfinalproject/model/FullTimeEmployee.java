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
public class FullTimeEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer workTime;
    @OneToOne(mappedBy = "fullTimeEmployee")
    private Employee employee;
    @OneToMany(mappedBy = "fullTimeEmployee")
    private Set<Vacation> vacations = new HashSet<>();

    public FullTimeEmployee(Integer workTime) {
        this.workTime = workTime;
    }
}
