package com.mas.pjatk.masfinalproject.service;

import com.mas.pjatk.masfinalproject.error.EntityNotFoundException;
import com.mas.pjatk.masfinalproject.error.VisitTimeConflictException;
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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;
    private final PatientRepository patientRepository;
    private final VetRepository vetRepository;


    public List<Visit> findAllVisits() {
        return visitRepository.findAll();
    }

    @Transactional
    public Visit saveVisit(CreateVisitCommand command) throws EntityNotFoundException, VisitTimeConflictException {
        Long vetId = command.getVetId();
        Vet vet = findVetByIdWithVisits(vetId);
        Long patientId = command.getPatientId();
        Patient patient = findPatientByIdWithVisits(patientId);
        LocalDate date = command.getDate();
        List<Visit> vetVisits = findByVet_IdAndDate(vetId, date);
        List<Visit> patientVisits = findByPatient_idAndDate(patientId, date);
        if (isTimeConflict(command, vetVisits) || isTimeConflict(command, patientVisits)) {
            throw new VisitTimeConflictException("VISIT_TIME_CONFLICT");
        }
        Visit visit = Visit.builder()
                .vet(vet)
                .patient(patient)
                .date(date)
                .startTime(command.getStartTime())
                .endTime(command.getEndTime())
                .charge(command.getCharge())
                .build();
        return visitRepository.saveAndFlush(visit);
    }

    private List<Visit> findByPatient_idAndDate(Long patientId, LocalDate date) {
        return visitRepository.findByPatient_IdAndDate(patientId, date);
    }

    private List<Visit> findByVet_IdAndDate(Long vetId, LocalDate date) {
        return visitRepository.findByVet_IdAndDate(vetId, date);
    }

    private Vet findVetByIdWithVisits(Long vetId) throws EntityNotFoundException {
        return vetRepository.findVetWithVisits(vetId)
                .orElseThrow(() -> new EntityNotFoundException("VET", vetId.toString()));
    }

    private Patient findPatientByIdWithVisits(Long patientId) throws EntityNotFoundException {
        return patientRepository.findByIdWithVisits(patientId)
                .orElseThrow(() -> new EntityNotFoundException("PATIENT_ID", patientId.toString()));
    }

    private boolean isTimeConflict(CreateVisitCommand command, List<Visit> visits) {
        return visits.stream().anyMatch(v -> fullTimeConflict(command, v));
    }

    private boolean fullTimeConflict(CreateVisitCommand command, Visit v) {
        return timeConflict(v.getStartTime(), command.getStartTime(), command.getEndTime())
                || timeConflict(v.getEndTime(), command.getStartTime(), command.getEndTime())
                || timeConflict(command.getStartTime(), v.getStartTime(), v.getEndTime())
                || timeConflict(command.getEndTime(), v.getStartTime(), v.getEndTime());
    }

    private boolean timeConflict(LocalTime lt1, LocalTime lt2, LocalTime lt3) {
        return lt1.isAfter(lt2) && lt1.isBefore(lt3);
    }
//(v.getStartTime().isAfter(command.getStartTime()) && v.getStartTime().isBefore(command.getEndTime()))
    //(v.getEndTime().isAfter(command.getStartTime()) && v.getEndTime().isBefore(command.getEndTime()))
    //(command.getStartTime.isAfter(v.getStartTime()) && command.getStarTime.isBefore(v.getEndTime()))
    //(command.getEndTime.isAfter(v.getStartTime()) && command.getEndTime.isBefore(v.getEndTime()))
}
