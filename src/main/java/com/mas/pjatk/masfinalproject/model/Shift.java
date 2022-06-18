package com.mas.pjatk.masfinalproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yaml.snakeyaml.events.Event;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    @ManyToOne
    @JoinColumn(name = "employee_id",nullable = false)
    private Employee  employee;

    public Shift(LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Shift{" +
                "date=" + date +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
