package com.mas.pjatk.masfinalproject.model.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDto {
    private Long id;
    private String firstname;
    private String lastname;
    private LocalDate birthDate;
    private String email;
    private String mobile;
}
