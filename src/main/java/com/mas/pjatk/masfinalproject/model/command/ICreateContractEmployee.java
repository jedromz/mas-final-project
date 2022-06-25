package com.mas.pjatk.masfinalproject.model.command;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ICreateContractEmployee {
    String getFirstname();

    String getLastname();

    LocalDate getBirthDate();

    BigDecimal getBonus();

    BigDecimal getRate();

    LocalDate getContractStart();

    LocalDate getContractEnd();
    Long getHospitalId();
}
