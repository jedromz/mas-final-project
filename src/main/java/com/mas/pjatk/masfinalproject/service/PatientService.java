package com.mas.pjatk.masfinalproject.service;

import com.mas.pjatk.masfinalproject.error.EntityNotFoundException;
import com.mas.pjatk.masfinalproject.model.Owner;
import com.mas.pjatk.masfinalproject.model.Patient;
import com.mas.pjatk.masfinalproject.model.command.CreatePatientCommand;
import com.mas.pjatk.masfinalproject.repository.OwnerRepository;
import com.mas.pjatk.masfinalproject.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final OwnerRepository ownerRepository;

    public Patient savePatient(Patient patient) {
        return patientRepository.saveAndFlush(patient);
    }

    @Transactional
    public Patient savePatient(CreatePatientCommand command) throws EntityNotFoundException {
        Owner owner = ownerRepository.findById(command.getOwnerId())
                .orElseThrow(() -> new EntityNotFoundException("OWNER", command.getOwnerId().toString()));
        Patient patient = Patient.builder()
                .name(command.getName())
                .race(command.getRace())
                .type(command.getType())
                .birthDate(command.getBirthDate())
                .owner(owner)
                .build();
        return patientRepository.saveAndFlush(patient);
    }
}
