package com.mas.pjatk.masfinalproject.mappings;

import com.mas.pjatk.masfinalproject.model.Employee;
import com.mas.pjatk.masfinalproject.model.command.CreateFullTimeAdminEmployeeCommand;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;

@Service
public class CreateEmployeeCommandToEmployee implements Converter<ICreateEmployeeCommand, Employee> {
    @Override
    public Employee convert(MappingContext<ICreateEmployeeCommand, Employee> mappingContext) {
        ICreateEmployeeCommand command = mappingContext.getSource();
        Employee employee = Employee.builder()
                .firstname(command.getFirstname())
                .lastname(command.getLastname())
                .birthDate(command.getBirthDate())
                .rate(command.getRate())
                .bonus(command.getBonus())
                .build();
        return employee;
    }
}
