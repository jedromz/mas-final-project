package com.mas.pjatk.masfinalproject.service;

import com.mas.pjatk.masfinalproject.error.EntityNotFoundException;
import com.mas.pjatk.masfinalproject.model.*;
import com.mas.pjatk.masfinalproject.model.command.*;
import com.mas.pjatk.masfinalproject.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
    private final HospitalRepository hospitalRepository;


    /**
     * @return all Employees
     */
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();

    }

    /**
     * @param command with parameters needed to create FullTimeEmployee Admin
     * @return created and savedobject
     * @throws EntityNotFoundException hospital_id
     */
    @Transactional
    public Employee addAdminEmployeeFullTime(CreateFullTimeAdminEmployeeCommand command) throws EntityNotFoundException {
        FullTimeEmployee fullTimeEmployee = buildFulltimeEmployee(command);
        fullTimeEmployee.setAdminEmployee(new AdminEmployee());
        return fullTimeEmployeeRepository.saveAndFlush(fullTimeEmployee);
    }


    /**
     * @param command with parameters needed to create ContractEmployee Admin
     * @return created and saved object
     * @throws EntityNotFoundException hospital_id
     */
    @Transactional
    public Employee addAdminEmployeeContract(CreateContractAdminEmployeeCommand command) throws EntityNotFoundException {
        ContractEmployee contractEmployee = buildContractEmployee(command);
        contractEmployee.setAdminEmployee(new AdminEmployee());
        return contractEmployeeRepository.saveAndFlush(contractEmployee);
    }

    /**
     * @param command with parameters needed to create FullTime Vet
     * @return created and saved object
     * @throws EntityNotFoundException hospital_id
     */
    @Transactional
    public Employee addVetFullTime(CreateFullTimeVetCommand command) throws EntityNotFoundException {
        FullTimeEmployee fullTimeEmployee = buildFulltimeEmployee(command);
        fullTimeEmployee.setVet(new Vet(command.getVetLicense(), command.getSpecialization()));
        return fullTimeEmployeeRepository.saveAndFlush(fullTimeEmployee);
    }

    /**
     * @param command with parameters needed to create Contract Vet
     * @return created and saved object
     * @throws EntityNotFoundException hospital_id
     */
    @Transactional
    public Employee addVetContract(CreateContractVetCommand command) throws EntityNotFoundException {
        ContractEmployee contractEmployee = buildContractEmployee(command);
        contractEmployee.setVet(new Vet(command.getVetLicense(), command.getSpecialization()));
        return contractEmployeeRepository.saveAndFlush(contractEmployee);
    }

    /**
     * @param command with parameters needed to create Contract Director
     * @return created and saved object
     * @throws EntityNotFoundException hospital_id
     */
    @Transactional
    public Employee addDirectorContract(CreateContractDirectorCommand command) throws EntityNotFoundException {
        ContractEmployee contractEmployee = buildContractEmployee(command);
        contractEmployee.setDirector(new Director(command.getTermStart(), command.getTermEnd()));
        return contractEmployeeRepository.saveAndFlush(contractEmployee);
    }

    /**
     * @param command with parameters needed to create Fulltime Director
     * @return created and saved object
     * @throws EntityNotFoundException hospital_id
     */
    @Transactional
    public Employee addDirectorFullTime(CreateFullTimeDirector command) throws EntityNotFoundException {
        FullTimeEmployee fullTimeEmployee = buildFulltimeEmployee(command);
        fullTimeEmployee.setDirector(new Director(command.getTermStart(), command.getTermEnd()));
        fullTimeEmployee.getDirector().setHospital(fullTimeEmployee.getHospital());
        return fullTimeEmployeeRepository.saveAndFlush(fullTimeEmployee);
    }

    /**
     * @param id    employee_id
     * @param email
     * @throws EntityNotFoundException employee_id
     */
    @Transactional
    public void addEmail(Long id, String email) throws EntityNotFoundException {
        Employee employee = getEmployee_id(id);
        employee.getEmails().add(email);
    }

    /**
     * @param id     employee_id
     * @param mobile
     * @throws EntityNotFoundException employee_id
     */
    @Transactional
    public void addMobile(Long id, String mobile) throws EntityNotFoundException {
        Employee employee = getEmployee_id(id);
        employee.getMobileNumbers().add(mobile);
    }


    /**
     * @param id
     * @param shift
     * @throws EntityNotFoundException
     */
    @Transactional
    public void addShift(Long id, Shift shift) throws EntityNotFoundException {
        Employee employee = getEmployee_id(id);
        employee.getShifts().add(shift);
    }

    /**
     * helper method to build FullTime Employees
     *
     * @param command FullTime Employee command
     * @return FullTimeEmployee
     * @throws EntityNotFoundException
     */
    private FullTimeEmployee buildFulltimeEmployee(ICreateFulltimeEmployeeCommand command) throws EntityNotFoundException {
        FullTimeEmployee emp = FullTimeEmployee.builder().firstname(command.getFirstname()).lastname(command.getLastname()).birthDate(command.getBirthDate()).rate(command.getRate()).bonus(command.getBonus()).workTime(command.getWorkTime()).build();
        emp.setHospital(hospitalRepository.findById(command.getHospitalId())
                .orElseThrow(() -> new EntityNotFoundException("HOSPITAL_ID", command.getHospitalId().toString())));
        return emp;
    }

    private Employee getEmployee_id(Long id) throws EntityNotFoundException {
        return employeeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("EMPLOYEE_ID", id.toString()));
    }

    /**
     * helper method to build ContractEmployees
     *
     * @param command FullTime Employee command
     * @return ContractEmployee
     * @throws EntityNotFoundException
     */
    private ContractEmployee buildContractEmployee(ICreateContractEmployee command) throws EntityNotFoundException {
        ContractEmployee emp = ContractEmployee.builder().firstname(command.getFirstname()).lastname(command.getLastname()).birthDate(command.getBirthDate()).bonus(command.getBonus()).rate(command.getRate()).contractStart(command.getContractStart()).contractEnd(command.getContractEnd()).build();
        emp.setHospital(hospitalRepository.findById(command.getHospitalId())
                .orElseThrow(() -> new EntityNotFoundException("HOSPITAL_ID", command.getHospitalId().toString())));
        return emp;
    }

    /**
     * deletes FullTime Employee and creates Contract Employee
     *
     * @param fullTimeEmployeeId
     * @param contractStart
     * @param contractEnd
     * @return saved Contract Employee
     * @throws EntityNotFoundException
     */
    @Transactional
    public ContractEmployee fromFullTimeToContract(Long fullTimeEmployeeId, LocalDate contractStart, LocalDate contractEnd) throws EntityNotFoundException {
        Employee employee = getEmployee_id(fullTimeEmployeeId);
        ContractEmployee contractEmployee = new ContractEmployee(employee.getFirstname(), employee.getLastname(), employee.getBirthDate(), employee.getRate(), employee.getBonus(), contractStart, contractEnd);
        AdminEmployee adminEmployee = employee.getAdminEmployee();
        Vet vet = employee.getVet();
        Director director = employee.getDirector();
        if (adminEmployee != null) {
            contractEmployee.setAdminEmployee(adminEmployee);
        }
        if (vet != null) {
            contractEmployee.setVet(vet);
        }
        if (director != null) {
            contractEmployee.setDirector(director);
        }
        employeeRepository.delete(employee);
        return employeeRepository.saveAndFlush(contractEmployee);
    }

    /**deletes Contract Employee and creates Fulltime Employee
     * @param contractEmployeeId
     *
     * @param workTime
     * @return saved FullTime Employee
     * @throws EntityNotFoundException
     */
    @Transactional
    public FullTimeEmployee contractToFullTime(Long contractEmployeeId, int workTime) throws EntityNotFoundException {
        Employee employee = getEmployee_id(contractEmployeeId);
        FullTimeEmployee fullTimeEmployee = new FullTimeEmployee(employee.getFirstname(), employee.getLastname(), employee.getBirthDate(), employee.getRate(), employee.getBonus(), workTime);
        AdminEmployee adminEmployee = employee.getAdminEmployee();
        Vet vet = employee.getVet();
        Director director = employee.getDirector();
        if (adminEmployee != null) {
            fullTimeEmployee.setAdminEmployee(adminEmployee);
        }
        if (vet != null) {
            fullTimeEmployee.setVet(vet);
        }
        if (director != null) {
            fullTimeEmployee.setDirector(director);
        }
        employeeRepository.delete(employee);
        return employeeRepository.saveAndFlush(fullTimeEmployee);
    }
}

