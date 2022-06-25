package com.mas.pjatk.masfinalproject.model.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VetDto {
    private Long id;
    private Long employeeId;
    private String firstname;
    private String lastname;
    private LocalDate birthDate;
    private BigDecimal rate;
    private BigDecimal bonus;
    private String vetLicense;
    private String specialization;
    private String email;
    private String mobile;
}
