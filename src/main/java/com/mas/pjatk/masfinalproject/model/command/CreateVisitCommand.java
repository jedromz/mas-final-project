package com.mas.pjatk.masfinalproject.model.command;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CreateVisitCommand {
    private Long vetId;
    private Long patientId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private BigDecimal charge;
}
