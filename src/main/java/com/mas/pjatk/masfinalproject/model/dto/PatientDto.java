package com.mas.pjatk.masfinalproject.model.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private String type;
    private String race;
    private Long ownerId;
}
