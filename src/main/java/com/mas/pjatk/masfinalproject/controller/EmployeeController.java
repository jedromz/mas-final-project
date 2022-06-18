package com.mas.pjatk.masfinalproject.controller;

import com.mas.pjatk.masfinalproject.model.dto.EmployeeDto;
import com.mas.pjatk.masfinalproject.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ModelMapper modelMapper;

    @GetMapping()
    public List<EmployeeDto> findAllEmployees2() {
        return employeeService.findAllEmployees().stream()
                .map(e -> modelMapper.map(e, EmployeeDto.class))
                .toList();
    }
}
