package com.mas.pjatk.masfinalproject.service;

import com.mas.pjatk.masfinalproject.error.EntityNotFoundException;
import com.mas.pjatk.masfinalproject.model.Patient;
import com.mas.pjatk.masfinalproject.model.Vet;
import com.mas.pjatk.masfinalproject.model.Visit;
import com.mas.pjatk.masfinalproject.model.command.CreateVisitCommand;
import com.mas.pjatk.masfinalproject.repository.PatientRepository;
import com.mas.pjatk.masfinalproject.repository.VetRepository;
import com.mas.pjatk.masfinalproject.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;
    private final PatientRepository patientRepository;
    private final VetRepository vetRepository;


    public List<Visit> findAllVisits(){
        return visitRepository.findAll();
    }

    @Transactional
    public Visit saveVisit(CreateVisitCommand command) throws EntityNotFoundException {
        Vet vet = vetRepository.findById(command.getVetId())
                .orElseThrow(() -> new EntityNotFoundException("VET", command.getVetId().toString()));
        Patient patient = patientRepository.findById(command.getPatientId())
                .orElseThrow(() -> new EntityNotFoundException("PATIENT", command.getPatientId().toString()));
        Visit visit = Visit.builder()
                .date(command.getDate())
                .startTime(command.getStartTime())
                .endTime(command.getEndTime())
                .vet(vet)
                .patient(patient)
                .build();
        return visitRepository.saveAndFlush(visit);
    }

}
