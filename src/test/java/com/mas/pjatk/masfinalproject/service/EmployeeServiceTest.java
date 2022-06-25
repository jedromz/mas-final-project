package com.mas.pjatk.masfinalproject.service;

import com.mas.pjatk.masfinalproject.model.*;
import com.mas.pjatk.masfinalproject.repository.*;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private ShiftRepository shiftRepository;
    @Mock
    private FullTimeEmployeeRepository fullTimeEmployeeRepository;
    @Mock
    private ContractEmployeeRepository contractEmployeeRepository;
    @Mock
    private VisitRepository visitRepository;
    @Mock
    private HospitalRepository hospitalRepository;

    private EmployeeService employeeService;
    private Employee EMP_1;
    private FullTimeEmployee FULL_TIME_1;
    private ContractEmployee CONTRACT_1;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeService = new EmployeeService(employeeRepository, shiftRepository, fullTimeEmployeeRepository
                , contractEmployeeRepository, visitRepository, hospitalRepository);

        EMP_1 = new FullTimeEmployee("TEST_NAME", "TEST_LASTNAME",
                LocalDate.now().minusYears(20), BigDecimal.valueOf(100.00), BigDecimal.valueOf(0), 160);
    }

    @Test
    @SneakyThrows
    public void shouldAddEmails() {
        Long id = 1L;
        String email1 = "test1@email.com";
        String email2 = "test2@email.com";
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.ofNullable(EMP_1));
        employeeService.addEmail(id, email1);
        employeeService.addEmail(id, email2);
        assertTrue(EMP_1.getEmails().containsAll(Set.of(email1, email2)));
    }

    @Test
    @SneakyThrows
    public void shouldAddMobiles() {
        Long id = 1L;
        String mobile1 = "600111234";
        String mobile2 = "600111235";
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.ofNullable(EMP_1));
        employeeService.addMobile(id, mobile1);
        employeeService.addMobile(id, mobile2);
        assertTrue(EMP_1.getMobileNumbers().containsAll(Set.of(mobile1, mobile2)));
    }

    @Test
    @SneakyThrows
    public void shouldAddShift() {
        Long id = 1L;
        Shift shift = new Shift(LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(8));
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.ofNullable(EMP_1));
        employeeService.addShift(id, shift);
        assertTrue(EMP_1.getShifts().contains(shift));
    }

    @Test
    public void overlappingTest() {
        LocalDate now = LocalDate.now();
        String specialization = "Cardiologist";
        Employee employee = fullTimeEmp();
        assertEquals(employee.getDirector().getTermStart(), now);
        assertEquals(employee.getVet().getSpecialization(), specialization);
    }


    private Employee fullTimeEmp() {
        LocalDate now = LocalDate.now();
        String license = "TEST_LICENSE_123";
        String specialization = "Cardiologist";
        Employee employee = new FullTimeEmployee("TEST_NAME", "TEST_LASTNAME",
                now.minusYears(30), BigDecimal.valueOf(350.00), BigDecimal.ZERO, 160);
        employee.setAdminEmployee(new AdminEmployee());
        employee.setDirector(new Director(now, now.plusYears(3)));
        employee.setVet(new Vet(license, specialization));
        return employee;
    }

    @AfterEach
    void tearDown() {
    }
}
