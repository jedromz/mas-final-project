package com.mas.pjatk.masfinalproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate termStart;
    private LocalDate termEnd;
    @OneToOne(mappedBy = "director")
    private Employee employee;

    public Director(LocalDate termStart, LocalDate termEnd) {
        this.termStart = termStart;
        this.termEnd = termEnd;
    }
}
