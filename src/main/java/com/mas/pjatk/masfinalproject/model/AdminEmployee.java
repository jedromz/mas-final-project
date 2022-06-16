package com.mas.pjatk.masfinalproject.model;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AdminEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private boolean deleted;
    @OneToOne(mappedBy = "adminEmployee")
    private Employee employee;
}
