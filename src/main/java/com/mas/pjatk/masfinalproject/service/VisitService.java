package com.mas.pjatk.masfinalproject.service;

import com.mas.pjatk.masfinalproject.model.command.CreateVisitCommand;
import com.mas.pjatk.masfinalproject.repository.PatientRepository;
import com.mas.pjatk.masfinalproject.repository.VetRepository;
import com.mas.pjatk.masfinalproject.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;
    private final PatientRepository patientRepository;
    private final VetRepository vetRepository;


    public void saveVisit(CreateVisitCommand command) {
        vetRepository.findById(command.getVetId())
                .orElseThrow(()->new EntityNotFoundException());
    }
}
