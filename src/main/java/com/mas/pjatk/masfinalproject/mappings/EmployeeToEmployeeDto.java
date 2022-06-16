package com.mas.pjatk.masfinalproject.mappings;

import com.mas.pjatk.masfinalproject.model.Employee;
import com.mas.pjatk.masfinalproject.model.dto.EmployeeDto;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;

@Service
public class EmployeeToEmployeeDto implements Converter<Employee, EmployeeDto> {
    @Override
    public EmployeeDto convert(MappingContext<Employee, EmployeeDto> mappingContext) {
        Employee employee = mappingContext.getSource();
        return EmployeeDto.builder()
                .id(employee.getId())
                .firstname(employee.getFirstname())
                .lastname(employee.getLastname())
                .birthDate(employee.getBirthDate())
                .rate(employee.getRate())
                .bonus(employee.getBonus())
                .build();
    }
}
