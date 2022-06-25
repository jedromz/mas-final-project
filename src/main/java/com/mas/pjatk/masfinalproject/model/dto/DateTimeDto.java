package com.mas.pjatk.masfinalproject.model.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DateTimeDto {
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
}
