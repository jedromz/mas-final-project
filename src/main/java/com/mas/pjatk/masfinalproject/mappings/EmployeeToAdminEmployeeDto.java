package com.mas.pjatk.masfinalproject.mappings;

import com.mas.pjatk.masfinalproject.model.AdminEmployee;
import com.mas.pjatk.masfinalproject.model.Employee;
import com.mas.pjatk.masfinalproject.model.dto.AdminEmployeeDto;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;

@Service
public class EmployeeToAdminEmployeeDto implements Converter<Employee, AdminEmployeeDto> {
    @Override
    public AdminEmployeeDto convert(MappingContext<Employee, AdminEmployeeDto> mappingContext) {
        Employee employee = mappingContext.getSource();;
        AdminEmployeeDto adminEmployeeDto = AdminEmployeeDto.builder()
                .employeeId(employee.getId())
                .adminEmployeeId(employee.getAdminEmployee().getId())
                .firstname(employee.getFirstname())
                .lastname(employee.getLastname())
                .birthDate(employee.getBirthDate())
                .rate(employee.getRate())
                .bonus(employee.getBonus())
                .build();
        return adminEmployeeDto;

    }
}
