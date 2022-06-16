package com.mas.pjatk.masfinalproject.mappings;

import com.mas.pjatk.masfinalproject.model.Employee;
import com.mas.pjatk.masfinalproject.model.command.CreateFullTimeAdminEmployeeCommand;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ICreateEmployeeCommand {
    String getFirstname();

    String getLastname();

    LocalDate getBirthDate();

    BigDecimal getRate();

    BigDecimal getBonus();
}
