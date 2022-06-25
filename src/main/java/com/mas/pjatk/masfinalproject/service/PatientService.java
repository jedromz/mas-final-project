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

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final OwnerRepository ownerRepository;

    public Patient savePatient(Patient patient) {
        return patientRepository.saveAndFlush(patient);
    }

    /**
     * Add patient and assign owner
     * @param command needed to create Patient
     * @return added and saved Patient
     * @throws EntityNotFoundException
     */
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

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }
    public Patient findById(Long id) throws EntityNotFoundException {
        return patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PATIENT_ID", id.toString()));
    }
}
