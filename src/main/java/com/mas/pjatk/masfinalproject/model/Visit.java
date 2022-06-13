package com.mas.pjatk.masfinalproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private BigDecimal charge;
    private boolean deleted;
    private boolean confirmed;
    private boolean cancelled;
    @ManyToOne
    @JoinColumn(name="vet_id", nullable=false)
    private Vet vet;
    @ManyToOne
    @JoinColumn(name="patient_id", nullable=false)
    private Patient patient;
}
