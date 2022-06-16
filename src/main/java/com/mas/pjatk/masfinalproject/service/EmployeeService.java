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

    private final AdminEmployeeRepository adminEmployeeRepository;
    private final VetRepository vetRepository;
    private final DirectorRepository directorRepository;
    private final FullTimeEmployeeRepository fullTimeEmployeeRepository;
    private final ContractEmployeeRepository contractEmployeeRepository;
    private final ModelMapper modelMapper;


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
