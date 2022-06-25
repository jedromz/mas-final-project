package com.mas.pjatk.masfinalproject.model.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PossibleVisitsCommand {
    @NotNull(message = "VET_ID_NOT_NULL")
    private Long vetId;
    @NotNull(message = "PATIENT_ID_NOT_NULL")
    private Long patientId;
    @NotNull(message = "DATE_NOT_NULL")
    private LocalDate date;
    @NotNull(message = "DURATION_NOT_NULL")
    private Integer durationInMinutes;
    @NotNull(message = "SIZE_NOT_NULL")
    private Integer size;
}
