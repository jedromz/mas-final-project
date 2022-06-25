package com.mas.pjatk.masfinalproject.model.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CreateFullTimeAdminEmployeeCommand implements ICreateFulltimeEmployeeCommand {
    @NotNull(message= "FIRSTNAME_NOT_NULL")
    private String firstname;
    @NotNull(message = "LASTNAME_NOT_NULL")
    private String lastname;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "BIRTHDATE_NOT_NULL")
    private LocalDate birthDate;
    @NotNull(message = "RATE_NOT_NULL")
    private BigDecimal rate;
    @NotNull(message = "BONUS_NOT_NULL")
    private BigDecimal bonus;
    @NotNull(message = "WORK_TIME_NOT_NULL")
    private Integer workTime;
    @NotNull(message = "HOSPITAL_ID_NOT_NULL")
    private Long hospitalId;
}
