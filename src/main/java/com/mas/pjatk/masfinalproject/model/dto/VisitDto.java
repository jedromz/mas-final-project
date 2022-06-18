package com.mas.pjatk.masfinalproject.model.dto;

import com.mas.pjatk.masfinalproject.model.Patient;
import com.mas.pjatk.masfinalproject.model.Vet;
import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VisitDto {
    private Long id;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private BigDecimal charge;
    private boolean deleted;
    private boolean confirmed;
    private boolean cancelled;
    private Long vetId;
    private Long patientId;
}
