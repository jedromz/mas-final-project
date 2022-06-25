package com.mas.pjatk.masfinalproject.model.command;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ICreateFulltimeEmployeeCommand {
    String getFirstname();

    String getLastname();

    LocalDate getBirthDate();

    BigDecimal getBonus();

    BigDecimal getRate();

    Integer getWorkTime();
    Long getHospitalId();
}
