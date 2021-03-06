package com.mas.pjatk.masfinalproject.controller;

import com.mas.pjatk.masfinalproject.model.dto.EmployeeDto;
import com.mas.pjatk.masfinalproject.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ModelMapper modelMapper;

//    @PostMapping("/admin-employees")
//    public ResponseEntity addAminEmployee(@RequestBody CreateFullTimeAdminEmployeeCommand command) {
//        Employee savedAdminEmployee = employeeService.addAdminEmployeeFullTime(command);
//        return new ResponseEntity(modelMapper.map(savedAdminEmployee, AdminEmployeeDto.class), HttpStatus.CREATED);
//    }


    @GetMapping()
    public List<EmployeeDto> findAllEmployees2() {
        return employeeService.findAllEmployees().stream()
                .map(e -> modelMapper.map(e, EmployeeDto.class))
                .toList();
    }

//    @GetMapping("/vets/available")
//    public List<EmployeeDto> findAvailableVets(@RequestParam LocalDateTime from, @RequestParam LocalDateTime to) {
//        employeeService.findAvailableVets(from,to);
//    }

//    @GetMapping("/admin-employees/{id}")
//    @SneakyThrows
//    public ResponseEntity getAdminEmployee(@PathVariable Long id) {
//        Employee adminEmployee = employeeService.findAdminEmployeeById(id);
//        return ResponseEntity.ok(modelMapper.map(adminEmployee, AdminEmployeeDto.class));
//    }
//
//    @GetMapping("/admin-employees")
//    public ResponseEntity getAllAdminEmployees(@PageableDefault Pageable pageable) {
//        return ResponseEntity.ok(employeeService.findAllAdminEmployees(pageable)
//                .map(s -> modelMapper.map(s, AdminEmployeeDto.class)));
//    }
//
//    @DeleteMapping("/admin-employees/{id}")
//    @SneakyThrows
//    public ResponseEntity deleteAdminEmployee(@PathVariable Long id) {
//        employeeService.softDeleteById(id);
//        return new ResponseEntity(HttpStatus.NO_CONTENT);
//    }
}
