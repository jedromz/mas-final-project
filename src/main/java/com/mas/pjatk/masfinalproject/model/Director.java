package com.mas.pjatk.masfinalproject.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
