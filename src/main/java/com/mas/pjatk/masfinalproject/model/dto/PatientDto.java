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
    private LocalDate birthdate;
    private Integer age;
    private String type;
    private String race;
    private String ownerEmail;
    private String ownerMobile;
}
