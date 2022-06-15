package com.mas.pjatk.masfinalproject.controller;

import com.mas.pjatk.masfinalproject.model.AdminEmployee;
import com.mas.pjatk.masfinalproject.model.Employee;
import com.mas.pjatk.masfinalproject.model.command.CreateAdminEmployeeCommand;
import com.mas.pjatk.masfinalproject.model.dto.AdminEmployeeDto;
import com.mas.pjatk.masfinalproject.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ModelMapper modelMapper;

    @PostMapping("/admin-employees")
    public ResponseEntity addAminEmployee(@RequestBody CreateAdminEmployeeCommand command) {
        Employee adminEmployee = modelMapper.map(command, Employee.class);
        AdminEmployee savedAdminEmployee = employeeService.addAdminEmployee(adminEmployee);
        return new ResponseEntity(modelMapper.map(savedAdminEmployee, AdminEmployeeDto.class), HttpStatus.CREATED);
    }
}
