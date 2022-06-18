package com.mas.pjatk.masfinalproject.service;

import com.mas.pjatk.masfinalproject.error.EntityNotFoundException;
import com.mas.pjatk.masfinalproject.model.*;
import com.mas.pjatk.masfinalproject.model.command.*;
import com.mas.pjatk.masfinalproject.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.MINUTES;


@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ShiftRepository shiftRepository;
    private final FullTimeEmployeeRepository fullTimeEmployeeRepository;
    private final ContractEmployeeRepository contractEmployeeRepository;
    private final VisitRepository visitRepository;


    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();

    }

    public List<Visit> findPossibleVisitsForVet(Long employeeId, LocalDateTime from, LocalDateTime to) throws EntityNotFoundException {
        Shift shift = shiftRepository.findByEmployee_IdAndDate(employeeId, from.toLocalDate())
                .orElseThrow(() -> new EntityNotFoundException("EMPLOYEE_ID", employeeId.toString()));
        List<Visit> existingVisits = visitRepository.findByVet_Employee_IdAndDate(employeeId, from.toLocalDate());
        LocalTime start = shift.getStartTime();
        LocalTime end = existingVisits.get(0).getStartTime();
        List<Visit> possibleVisits = new ArrayList<>();
        findFreeInterval(from, to, shift, existingVisits, start, end, possibleVisits);
        return possibleVisits;
    }

    private void findFreeInterval(LocalDateTime from, LocalDateTime to, Shift shift, List<Visit> existingVisits, LocalTime start, LocalTime end, List<Visit> possibleVisits) {
        addVisitIfFreeInterval(start, end, from, to, possibleVisits);
        for (int i = 0; i < existingVisits.size() - 1; i++) {
            start = existingVisits.get(i).getEndTime();
            end = existingVisits.get(i + 1).getStartTime();
            addVisitIfFreeInterval(start, end, from, to, possibleVisits);
        }
        start = existingVisits.get(existingVisits.size() - 1).getEndTime();
        end = shift.getEndTime();
        addVisitIfFreeInterval(start, end, from, to, possibleVisits);
    }

    private void addVisitIfFreeInterval(LocalTime start, LocalTime end, LocalDateTime from, LocalDateTime to, List<Visit> possibleVisits) {
        if (MINUTES.between(start, end) > MINUTES.between(from, to)) {
            possibleVisits.add(Visit.builder()
                    .date(from.toLocalDate())
                    .startTime(start)
                    .endTime(start.plusMinutes(MINUTES.between(from, to)))
                    .build());
        }
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
