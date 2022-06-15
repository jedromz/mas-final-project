package com.mas.pjatk.masfinalproject.mappings;

import com.mas.pjatk.masfinalproject.model.AdminEmployee;
import com.mas.pjatk.masfinalproject.model.Employee;
import com.mas.pjatk.masfinalproject.model.dto.AdminEmployeeDto;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;

@Service
public class AdminEmployeeToAdminEmployeeDto implements Converter<AdminEmployee, AdminEmployeeDto> {
    @Override
    public AdminEmployeeDto convert(MappingContext<AdminEmployee, AdminEmployeeDto> mappingContext) {
        AdminEmployee adminEmployee = mappingContext.getSource();
        Employee employee = adminEmployee.getEmployee();
        AdminEmployeeDto adminEmployeeDto = AdminEmployeeDto.builder()
                .firstname(employee.getFirstname())
                .lastname(employee.getLastname())
                .birthDate(employee.getBirthDate())
                .rate(employee.getRate())
                .bonus(employee.getBonus())
                .build();
        return adminEmployeeDto;

    }
}
