package com.mas.pjatk.masfinalproject.service;

import com.mas.pjatk.masfinalproject.error.EntityNotFoundException;
import com.mas.pjatk.masfinalproject.mappings.ICreateEmployeeCommand;
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

import java.time.LocalDate;
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
        Employee employee = buildEmployee(command);
        AdminEmployee adminEmployee = new AdminEmployee();
        FullTimeEmployee fullTimeEmployee = buildFullTimeEmployee(command.getWorkTime());
        //save entities
        Employee savedEmployee = employeeRepository.saveAndFlush(employee);
        AdminEmployee savedAdminEmployee = adminEmployeeRepository.saveAndFlush(adminEmployee);
        FullTimeEmployee savedFullTimeEmployee = fullTimeEmployeeRepository.saveAndFlush(fullTimeEmployee);
        //associations
        savedEmployee.setAdminEmployee(savedAdminEmployee);
        savedEmployee.setFullTimeEmployee(savedFullTimeEmployee);
        return savedEmployee;
    }


    @Transactional
    public Employee addAdminEmployeeContract(CreateContractAdminEmployeeCommand command) {
        //create entities
        Employee employee = buildEmployee(command);
        AdminEmployee adminEmployee = new AdminEmployee();
        ContractEmployee contractEmployee = buildContractEmployee(command.getContractStart(), command.getContractEnd());
        //save entities
        Employee savedEmployee = employeeRepository.saveAndFlush(employee);
        AdminEmployee savedAdminEmployee = adminEmployeeRepository.saveAndFlush(adminEmployee);
        ContractEmployee savedContractEmployee = contractEmployeeRepository.saveAndFlush(contractEmployee);
        //associations
        savedEmployee.setAdminEmployee(savedAdminEmployee);
        savedEmployee.setContractEmployee(savedContractEmployee);
        return savedEmployee;
    }

    private ContractEmployee buildContractEmployee(LocalDate command, LocalDate command1) {
        return new ContractEmployee(command, command1);
    }

    @Transactional
    public Employee addVetFullTime(CreateFullTimeVetCommand command) {
        //create entities
        Employee employee = buildEmployee(command);
        Vet vet = buildVet(command.getVetLicense(), command.getSpecialization());
        FullTimeEmployee fullTimeEmployee = buildFullTimeEmployee(command.getWorkTime());
        //save entities
        Employee savedEmployee = employeeRepository.saveAndFlush(employee);
        Vet savedVet = vetRepository.saveAndFlush(vet);
        FullTimeEmployee savedFullTimeEmployee = fullTimeEmployeeRepository.saveAndFlush(fullTimeEmployee);
        //associations
        savedEmployee.setVet(vet);
        savedEmployee.setFullTimeEmployee(savedFullTimeEmployee);
        return savedEmployee;
    }

    @Transactional
    public Employee addVetContract(CreateContractVetCommand command) {
        //create entities
        Vet vet = buildVet(command.getVetLicense(), command.getSpecialization());
        Employee employee = buildEmployee(command);
        ContractEmployee contractEmployee = buildContractEmployee(command.getContractStart(), command.getContractEnd());
        //save entities
        Employee savedEmployee = employeeRepository.saveAndFlush(employee);
        Vet savedVet = vetRepository.saveAndFlush(vet);
        ContractEmployee savedContractEmployee = contractEmployeeRepository.saveAndFlush(contractEmployee);
        //associations
        employee.setVet(savedVet);
        employee.setContractEmployee(savedContractEmployee);
        return savedEmployee;
    }

    @Transactional
    public Employee addDirectorContract(CreateContractDirectorCommand command) {
        //create entities
        Employee employee = buildEmployee(command);
        Director director = buildDirector(command.getTermStart(), command.getTermEnd());
        ContractEmployee contractEmployee = buildContractEmployee(command.getContractStart(), command.getContractEnd());
        //save entities
        Employee savedEmployee = employeeRepository.saveAndFlush(employee);
        Director savedDirector = directorRepository.saveAndFlush(director);
        ContractEmployee savedContractEmployee = contractEmployeeRepository.saveAndFlush(contractEmployee);
        //associations
        savedEmployee.setDirector(savedDirector);
        savedEmployee.setContractEmployee(savedContractEmployee);
        return savedEmployee;
    }


    @Transactional
    public Employee addDirectorFullTime(CreateFullTimeDirector command) {
        //create entities
        Director director = buildDirector(command.getTermStart(), command.getTermEnd());
        Employee employee = buildEmployee(command);
        FullTimeEmployee fullTimeEmployee = buildFullTimeEmployee(command.getWorkTime());
        //save entities
        Employee savedEmployee = employeeRepository.saveAndFlush(employee);
        Director savedDirector = directorRepository.saveAndFlush(director);
        FullTimeEmployee savedFulltimeEmployee = fullTimeEmployeeRepository.saveAndFlush(fullTimeEmployee);
        //associations
        employee.setDirector(savedDirector);
        employee.setFullTimeEmployee(savedFulltimeEmployee);
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

    private Employee buildEmployee(ICreateEmployeeCommand command) {
        return Employee.builder()
                .firstname(command.getFirstname())
                .lastname(command.getLastname())
                .birthDate(command.getBirthDate())
                .rate(command.getRate())
                .bonus(command.getBonus())
                .build();
    }

    private Vet buildVet(String command, String command1) {
        return new Vet(command, command1);
    }

    private FullTimeEmployee buildFullTimeEmployee(Integer command) {
        return new FullTimeEmployee(command);
    }

    private Director buildDirector(LocalDate command, LocalDate command1) {
        return new Director(command, command1);
    }
}
