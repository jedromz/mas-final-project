package com.mas.pjatk.masfinalproject.mappings;

import com.mas.pjatk.masfinalproject.model.AdminEmployee;
import com.mas.pjatk.masfinalproject.model.Employee;
import com.mas.pjatk.masfinalproject.model.command.CreateAdminEmployeeCommand;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateAdminEmployeeToAdminEmployee implements Converter<CreateAdminEmployeeCommand, Employee> {
    @Override
    public Employee convert(MappingContext<CreateAdminEmployeeCommand, Employee> mappingContext) {
        CreateAdminEmployeeCommand command = mappingContext.getSource();
        Employee employee = Employee.builder()
                .firstname(command.getFirstname())
                .lastname(command.getLastname())
                .birthDate(command.getBirthDate())
                .rate(command.getRate())
                .bonus(command.getBonus())
                .build();
        AdminEmployee adminEmployee = new AdminEmployee();
        adminEmployee.setEmployee(employee);
        employee.setAdminEmployee(adminEmployee);
        return employee;
    }
}
