package com.mas.pjatk.masfinalproject.model.command;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CreateVisitCommand {
    private Long vetId;
    private Long patientId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private BigDecimal charge;
}
