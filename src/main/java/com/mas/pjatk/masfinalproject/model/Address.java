package com.mas.pjatk.masfinalproject.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String streetName;
    private String buildingNumber;
    private String postCode;
    private String city;
    @OneToOne(mappedBy = "address")
    private Hospital hospital;

    public Address(String streetName, String buildingNumber, String postCode, String city) {
        this.streetName = streetName;
        this.buildingNumber = buildingNumber;
        this.postCode = postCode;
        this.city = city;
    }
}
