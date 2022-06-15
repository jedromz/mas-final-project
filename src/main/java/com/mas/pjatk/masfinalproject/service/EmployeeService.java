package com.mas.pjatk.masfinalproject.service;

import com.mas.pjatk.masfinalproject.model.AdminEmployee;
import com.mas.pjatk.masfinalproject.model.Employee;
import com.mas.pjatk.masfinalproject.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public AdminEmployee addAdminEmployee(Employee employee) {
        Employee savedEmployee = employeeRepository.saveAndFlush(employee);
        return savedEmployee.getAdminEmployee();
    }
}
