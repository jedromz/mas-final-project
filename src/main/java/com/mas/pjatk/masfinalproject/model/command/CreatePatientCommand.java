package com.mas.pjatk.masfinalproject.model.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CreatePatientCommand {
    @NotNull(message = "NAME_NOT_NULL")
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "BIRTHDATE_NOT_NULL")
    private LocalDate birthDate;
    @NotNull(message = "TYPE_NOT_NULL")
    private String type;
    @NotNull(message = "RACE_NOT_NULL")
    private String race;
    @NotNull(message = "OWNER_ID_NOT_NULL")
    private Long ownerId;
}
