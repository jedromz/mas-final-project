package com.mas.pjatk.masfinalproject.model.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CreatePatientCommand {
    private String name;
    private LocalDate birthDate;
    private String type;
    private String race;
    private Long ownerId;
}
