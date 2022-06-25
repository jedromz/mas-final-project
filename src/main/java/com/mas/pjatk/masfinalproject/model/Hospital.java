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
public class Hospital {

    public static final Integer BASE_VISIT_COST = 100;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean deleted;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
    @OneToMany(mappedBy = "hospital")
    private Set<Room> rooms = new HashSet<>();
    @OneToMany(mappedBy = "hospital")
    private Set<Employee> employees = new HashSet<>();


    public Hospital(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}
