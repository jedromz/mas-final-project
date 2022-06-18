package com.mas.pjatk.masfinalproject.service;

import com.mas.pjatk.masfinalproject.model.*;
import com.mas.pjatk.masfinalproject.model.command.*;
import com.mas.pjatk.masfinalproject.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static java.lang.Math.toIntExact;
import static java.time.temporal.ChronoUnit.MINUTES;


@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

    private final AdminEmployeeRepository adminEmployeeRepository;
    private final EmployeeRepository employeeRepository;
    private final VetRepository vetRepository;
    private final DirectorRepository directorRepository;
    private final FullTimeEmployeeRepository fullTimeEmployeeRepository;
    private final ContractEmployeeRepository contractEmployeeRepository;


    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();

    }

    public List<Vet> findAvailableVets(LocalDateTime from, LocalDateTime to) {
        return vetRepository.findByVisits_DateAndVisits_StartTimeIsBetweenAndVisits_EndTimeIsBetween(from.toLocalDate(), from.toLocalTime(), to.toLocalTime());
    }

    public List<Visit> getFreeIntervals(LocalDateTime from, LocalDateTime to) {

    }

    @Transactional
    public Employee addAdminEmployeeFullTime(CreateFullTimeAdminEmployeeCommand command) {
        FullTimeEmployee fullTimeEmployee = buildFulltimeEmployee(command);
        fullTimeEmployee.setAdminEmployee(new AdminEmployee());
        return fullTimeEmployeeRepository.saveAndFlush(fullTimeEmployee);
    }


    @Transactional
    public Employee addAdminEmployeeContract(CreateContractAdminEmployeeCommand command) {
        ContractEmployee contractEmployee = buildContractEmployee(command);
        contractEmployee.setAdminEmployee(new AdminEmployee());
        return contractEmployeeRepository.saveAndFlush(contractEmployee);
    }

    @Transactional
    public Employee addVetFullTime(CreateFullTimeVetCommand command) {
        FullTimeEmployee fullTimeEmployee = buildFulltimeEmployee(command);
        fullTimeEmployee.setVet(new Vet(command.getVetLicense(), command.getSpecialization()));
        return fullTimeEmployeeRepository.saveAndFlush(fullTimeEmployee);
    }

    @Transactional
    public Employee addVetContract(CreateContractVetCommand command) {
        ContractEmployee contractEmployee = buildContractEmployee(command);
        contractEmployee.setVet(new Vet(command.getVetLicense(), command.getSpecialization()));
        return contractEmployeeRepository.saveAndFlush(contractEmployee);
    }

    @Transactional
    public Employee addDirectorContract(CreateContractDirectorCommand command) {
        ContractEmployee contractEmployee = buildContractEmployee(command);
        contractEmployee.setDirector(new Director(command.getTermStart(), command.getTermEnd()));
        return contractEmployeeRepository.saveAndFlush(contractEmployee);
    }


    @Transactional
    public Employee addDirectorFullTime(CreateFullTimeDirector command) {
        FullTimeEmployee fullTimeEmployee = buildFulltimeEmployee(command);
        fullTimeEmployee.setDirector(new Director(command.getTermStart(), command.getTermEnd()));
        return fullTimeEmployeeRepository.saveAndFlush(fullTimeEmployee);
    }

    private FullTimeEmployee buildFulltimeEmployee(ICreateFulltimeEmployeeCommand command) {
        return FullTimeEmployee.builder().firstname(command.getFirstname()).lastname(command.getLastname()).birthDate(command.getBirthDate()).rate(command.getRate()).bonus(command.getBonus()).workTime(command.getWorkTime()).build();
    }

    private ContractEmployee buildContractEmployee(ICreateContractEmployee command) {
        return ContractEmployee.builder().firstname(command.getFirstname()).lastname(command.getLastname()).birthDate(command.getBirthDate()).bonus(command.getBonus()).rate(command.getRate()).contractStart(command.getContractStart()).contractEnd(command.getContractEnd()).build();
    }
}
