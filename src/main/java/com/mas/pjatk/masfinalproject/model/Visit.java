package com.mas.pjatk.masfinalproject.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
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

    public Visit(LocalDate date, LocalTime startTime, LocalTime endTime, BigDecimal charge) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.charge = charge;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "date=" + date +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
