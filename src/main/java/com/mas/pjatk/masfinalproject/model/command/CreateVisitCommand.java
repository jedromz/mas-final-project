package com.mas.pjatk.masfinalproject.model.command;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CreateVisitCommand {
    @NotNull(message = "VET_ID_NOT_NULL")
    private Long vetId;
    @NotNull(message = "PATIENT_ID_NOT_NULL")
    private Long patientId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "DATE_NOT_NULL")
    @FutureOrPresent
    private LocalDate date;
    @NotNull(message = "START_TIME_NOT_NULL")
    private LocalTime startTime;
    @NotNull(message = "END_TIME_NOT_NULL")
    private LocalTime endTime;
    @NotNull(message = "CHARGE_NOT_NULL")
    private BigDecimal charge;
}
