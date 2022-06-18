package com.mas.pjatk.masfinalproject.service;

import com.mas.pjatk.masfinalproject.error.VisitTimeConflictException;
import com.mas.pjatk.masfinalproject.model.Patient;
import com.mas.pjatk.masfinalproject.model.Vet;
import com.mas.pjatk.masfinalproject.model.Visit;
import com.mas.pjatk.masfinalproject.model.command.CreateVisitCommand;
import com.mas.pjatk.masfinalproject.repository.PatientRepository;
import com.mas.pjatk.masfinalproject.repository.VetRepository;
import com.mas.pjatk.masfinalproject.repository.VisitRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class VisitServiceTest {

    private VisitService visitService;
    @Mock
    private VisitRepository visitRepository;
    @Mock
    private PatientRepository patientRepository;
    @Mock
    private VetRepository vetRepository;
    private Vet TEST_VET_1;
    private Patient TEST_PATIENT_1;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private BigDecimal charge;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        date = LocalDate.now();
        startTime = LocalTime.now();
        endTime = startTime.plusMinutes(15);
        charge = BigDecimal.valueOf(140.0);
        visitService = new VisitService(visitRepository, patientRepository, vetRepository);
        TEST_VET_1 = new Vet("TEST", "TEST");
        TEST_PATIENT_1 = new Patient("TEST", date.minusYears(10), "TEST", "TEST");
        TEST_PATIENT_1.getVisits().add(new Visit(date, startTime, endTime, charge));
        TEST_VET_1.getVisits().add(new Visit(date, startTime, endTime, charge));
    }

    @AfterEach
    void tearDown() {
        vetRepository.deleteAll();
    }

    @Test
    void saveNotSaveVisit() {
        VisitTimeConflictException thrown = Assertions.assertThrows(VisitTimeConflictException.class, () -> {
            CreateVisitCommand command = CreateVisitCommand.builder()
                    .vetId(1L)
                    .patientId(1L)
                    .date(date)
                    .startTime(startTime.plusMinutes(1))
                    .endTime(endTime.minusMinutes(1))
                    .charge(charge)
                    .build();
            when(visitRepository.findByVet_IdAndDate(anyLong(), any())).thenReturn(new ArrayList<>(TEST_VET_1.getVisits()));
            when(visitRepository.findByPatient_IdAndDate(anyLong(), any())).thenReturn(new ArrayList<>(TEST_PATIENT_1.getVisits()));
            when(vetRepository.findVetWithVisits(anyLong())).thenReturn(Optional.of(TEST_VET_1));
            when(patientRepository.findByIdWithVisits(anyLong())).thenReturn(Optional.of(TEST_PATIENT_1));
            visitService.saveVisit(command);
        });
        Assertions.assertEquals("VISIT_TIME_CONFLICT", thrown.getMessage());
    }

    @SneakyThrows
    @Test
    void saveSaveVisit() {
        CreateVisitCommand command = CreateVisitCommand.builder()
                .vetId(1L)
                .patientId(1L)
                .date(date)
                .startTime(startTime.plusHours(1))
                .endTime(endTime.plusHours(1).plusMinutes(15))
                .charge(charge)
                .build();
        when(visitRepository.findByVet_IdAndDate(anyLong(), any())).thenReturn(new ArrayList<>(TEST_VET_1.getVisits()));
        when(visitRepository.findByPatient_IdAndDate(anyLong(), any())).thenReturn(new ArrayList<>(TEST_PATIENT_1.getVisits()));
        when(vetRepository.findVetWithVisits(anyLong())).thenReturn(Optional.of(TEST_VET_1));
        when(patientRepository.findByIdWithVisits(anyLong())).thenReturn(Optional.of(TEST_PATIENT_1));
        visitService.saveVisit(command);
    }
}
