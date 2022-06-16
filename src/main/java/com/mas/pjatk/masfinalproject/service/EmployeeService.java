package com.mas.pjatk.masfinalproject.service;

import com.mas.pjatk.masfinalproject.error.EntityNotFoundException;
import com.mas.pjatk.masfinalproject.model.*;
import com.mas.pjatk.masfinalproject.model.command.*;
import com.mas.pjatk.masfinalproject.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;


@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AdminEmployeeRepository adminEmployeeRepository;
    private final VetRepository vetRepository;
    private final DirectorRepository directorRepository;
    private final FullTimeEmployeeRepository fullTimeEmployeeRepository;
    private final ContractEmployeeRepository contractEmployeeRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public Page<Employee> findAllAdminEmployees(Pageable pageable) {
        return employeeRepository.findByAdminEmployeeIsNotNull(pageable);
    }

    @Transactional
    public Employee addAdminEmployeeFullTime(CreateFullTimeAdminEmployeeCommand command) {
        //create entities
        Employee employee = modelMapper.map(command, Employee.class);
        AdminEmployee adminEmployee = new AdminEmployee();
        FullTimeEmployee fullTimeEmployee = new FullTimeEmployee(command.getWorkTime());
        //save entities
        Employee savedEmployee = employeeRepository.saveAndFlush(employee);
        AdminEmployee savedAdminEmployee = adminEmployeeRepository.saveAndFlush(adminEmployee);
        fullTimeEmployeeRepository.save(fullTimeEmployeeRepository);
        //associations
        employee.setAdminEmployee(savedAdminEmployee);
        employee.setFullTimeEmployee(fullTimeEmployee);
        adminEmployee.setEmployee(savedEmployee);
        fullTimeEmployee.setEmployee(savedEmployee);
        return savedEmployee;
    }


    @Transactional
    public Employee addAdminEmployeeContract(CreateContractAdminEmployeeCommand command) {
        //create entities
        Employee employee = modelMapper.map(command, Employee.class);
        AdminEmployee adminEmployee = new AdminEmployee();
        ContractEmployee contractEmployee = new ContractEmployee(command.getContractStart(), command.getContractEnd());
        //save entities
        Employee savedEmployee = employeeRepository.saveAndFlush(employee);
        AdminEmployee savedAdminEmployee = adminEmployeeRepository.saveAndFlush(adminEmployee);
        contractEmployeeRepository.save(contractEmployee);
        //associations
        employee.setAdminEmployee(savedAdminEmployee);
        employee.setContractEmployee(contractEmployee);
        adminEmployee.setEmployee(savedEmployee);
        contractEmployee.setEmployee(savedEmployee);
        return savedEmployee;
    }

    @Transactional
    public Employee addVetFullTime(CreateFullTimeVetCommand command) {
        //create entities
        Vet vet = new Vet(command.getVetLicense(), command.getSpecialization());
        Employee employee = modelMapper.map(command, Employee.class);
        FullTimeEmployee fullTimeEmployee = new FullTimeEmployee(command.getWorkTime());
        //save entities
        Employee savedEmployee = employeeRepository.saveAndFlush(employee);
        Vet savedVet = vetRepository.saveAndFlush(vet);
        fullTimeEmployeeRepository.saveAndFlush(fullTimeEmployeeRepository);
        //associations
        employee.setVet(savedVet);
        employee.setFullTimeEmployee(fullTimeEmployee);
        vet.setEmployee(savedEmployee);
        fullTimeEmployee.setEmployee(savedEmployee);
        return savedEmployee;
    }

    @Transactional
    public Employee addDirectorContract(CreateContractDirectorCommand command) {
        //create entities
        Employee employee = modelMapper.map(command, Employee.class);
        AdminEmployee adminEmployee = new AdminEmployee();
        ContractEmployee contractEmployee = new ContractEmployee(command.getContractStart(), command.getContractEnd());
        //save entities
        Employee savedEmployee = employeeRepository.saveAndFlush(employee);
        AdminEmployee savedAdminEmployee = adminEmployeeRepository.saveAndFlush(adminEmployee);
        contractEmployeeRepository.save(contractEmployee);
        //associations
        employee.setAdminEmployee(savedAdminEmployee);
        employee.setContractEmployee(contractEmployee);
        adminEmployee.setEmployee(savedEmployee);
        contractEmployee.setEmployee(savedEmployee);
        return savedEmployee;
    }


    @Transactional
    public Employee addDirectorFullTime(CreateFullTimeDirector command) {
        //create entities
        Director director = new Director(command.getTermStart(), command.getTermEnd());
        Employee employee = modelMapper.map(command, Employee.class);
        FullTimeEmployee fullTimeEmployee = new FullTimeEmployee(command.getWorkTime());
        //save entities
        Employee savedEmployee = employeeRepository.saveAndFlush(employee);
        Director savedDirector = directorRepository.saveAndFlush(director);
        fullTimeEmployeeRepository.saveAndFlush(fullTimeEmployeeRepository);
        //associations
        employee.setDirector(director);
        employee.setFullTimeEmployee(fullTimeEmployee);
        director.setEmployee(savedEmployee);
        fullTimeEmployee.setEmployee(savedEmployee);
        return savedEmployee;
    }


    @Transactional
    public Employee addVetContract(CreateContractVetCommand command) {
        //create entities
        Vet vet = new Vet(command.getVetLicense(), command.getSpecialization());
        Employee employee = modelMapper.map(command, Employee.class);
        ContractEmployee contractEmployee = new ContractEmployee(command.getContractStart(), command.getContractEnd());
        //save entities
        Employee savedEmployee = employeeRepository.saveAndFlush(employee);
        Vet savedVet = vetRepository.saveAndFlush(vet);
        contractEmployeeRepository.save(contractEmployee);
        //associations
        employee.setVet(savedVet);
        employee.setContractEmployee(contractEmployee);
        vet.setEmployee(savedEmployee);
        contractEmployee.setEmployee(savedEmployee);
        return savedEmployee;
    }

    @Transactional
    public void softDeleteById(Long id) throws EntityNotFoundException {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("EMPLOYEE", id.toString()));
        employee.setDeleted(true);
        employee.getAdminEmployee().setDeleted(true);
    }

    @Transactional(readOnly = true)
    public Employee findAdminEmployeeById(Long id) throws EntityNotFoundException {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("EMPLOYEE", id.toString()));
    }

    @Transactional(readOnly = true)
    public Set<Shift> getShiftsById(Long id) throws EntityNotFoundException {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("EMPLOYEE", id.toString()));
        return employee.getShifts();
    }

    @Transactional(readOnly = true)
    public Set<Vacation> getVacationsById(Long id) throws EntityNotFoundException {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("EMPLOYEE", id.toString()));
        return employee.getFullTimeEmployee().getVacations();
    }
}
