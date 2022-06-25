package com.mas.pjatk.masfinalproject.service;

import com.mas.pjatk.masfinalproject.error.EntityNotFoundException;
import com.mas.pjatk.masfinalproject.error.VisitTimeConflictException;
import com.mas.pjatk.masfinalproject.model.Patient;
import com.mas.pjatk.masfinalproject.model.Vet;
import com.mas.pjatk.masfinalproject.model.Visit;
import com.mas.pjatk.masfinalproject.model.command.CreateVisitCommand;
import com.mas.pjatk.masfinalproject.model.dto.DateTimeDto;
import com.mas.pjatk.masfinalproject.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;
    private final PatientRepository patientRepository;
    private final VetRepository vetRepository;
    private final ShiftRepository shiftRepository;
    private final VacationRepository vacationRepository;

    public List<Visit> findAllVisits() {
        return visitRepository.findAll();
    }

    /**
     * adds new visit, checks if it can be added (vet visits,shifts,vacations and patient visits)
     *
     * @param command Create Visit
     * @return saved Visit
     * @throws EntityNotFoundException    vet_id, patient_id
     * @throws VisitTimeConflictException patient or vet time conflict
     */
    @Transactional
    public Visit saveVisit(CreateVisitCommand command) throws EntityNotFoundException, VisitTimeConflictException {
        Long vetId = command.getVetId();
        Vet vet = findVetByIdWithVisits(vetId);
        Long patientId = command.getPatientId();
        Patient patient = findPatientByIdWithVisits(patientId);
        LocalDate date = command.getDate();
        List<Visit> vetVisits = findByVet_IdAndDate(vetId, date);
        List<Visit> patientVisits = findByPatient_idAndDate(patientId, date);
        LocalTime startTime = command.getStartTime();
        LocalTime endTime = command.getEndTime();
        if (endTime.isBefore(endTime)) {
            throw new VisitTimeConflictException("END_BEFORE_START");
        }
        if (((isTimeConflict(command, vetVisits) || isTimeConflict(command, patientVisits))
                || visitExistsByVetPatientDateStartEndTime(vetId, patientId, date, startTime, endTime))
                || !shiftRepository.existsByDateAfterAndEmployee_IdAndStartTimeBeforeAndEndTimeAfter(command.getDate(), vet.getEmployee().getId(), command.getStartTime(), command.getEndTime())
                || vacationRepository.existsByFullTimeEmployee_IdAndStartBetweenAndEndBetween(vet.getEmployee().getId(), date)) {
            throw new VisitTimeConflictException("VISIT_TIME_CONFLICT");
        }
        Visit visit = Visit.builder()
                .vet(vet)
                .patient(patient)
                .date(date)
                .startTime(startTime)
                .endTime(endTime)
                .charge(command.getCharge())
                .build();
        return visitRepository.saveAndFlush(visit);
    }

    /**
     * finds top 'size' possible visits
     *
     * @param patientId
     * @param vetId
     * @param fromDate
     * @param size
     * @param duration
     * @return Set of DateTimes for possible visits
     * @throws EntityNotFoundException
     * @throws VisitTimeConflictException
     */
    public Set<DateTimeDto> findPossibleVisits(Long patientId, Long vetId, LocalDate fromDate, int size, int duration) throws EntityNotFoundException, VisitTimeConflictException {
        Set<DateTimeDto> possibleStartTimes = new HashSet<>();
        for (LocalDate date = fromDate; date.isBefore(fromDate.plusDays(7)); date = date.plusDays(1)) {
            LocalDateTime dateTime;
            for (LocalTime fromTime = LocalTime.of(9, 0); fromTime.isBefore(LocalTime.of(22, 0)); fromTime = fromTime.plusMinutes(duration)) {
                if (possibleStartTimes.size() >= size)
                    break;
                dateTime = LocalDateTime.of(date, fromTime);
                if (isVisitPossible(CreateVisitCommand.builder().vetId(vetId).patientId(patientId).date(fromDate).startTime(fromTime).endTime(fromTime.plusMinutes(duration)).build())) {
                    possibleStartTimes.add(new DateTimeDto(date, fromTime, fromTime.plusMinutes(duration)));
                }
            }
        }

        return possibleStartTimes;
    }

    /**
     * check if visit is possible
     *
     * @param command CreateVisit
     * @return boolean
     * @throws EntityNotFoundException    vet_id, patient_id
     * @throws VisitTimeConflictException vet or patient time conflict
     */
    public boolean isVisitPossible(CreateVisitCommand command) throws EntityNotFoundException, VisitTimeConflictException {
        Long vetId = command.getVetId();
        Vet vet = findVetByIdWithVisits(vetId);
        Long patientId = command.getPatientId();
        Patient patient = findPatientByIdWithVisits(patientId);
        LocalDate date = command.getDate();
        List<Visit> vetVisits = findByVet_IdAndDate(vetId, date);
        List<Visit> patientVisits = findByPatient_idAndDate(patientId, date);
        LocalTime startTime = command.getStartTime();
        LocalTime endTime = command.getEndTime();
        return (!isTimeConflict(command, vetVisits) && !isTimeConflict(command, patientVisits))
                && !visitExistsByVetPatientDateStartEndTime(vetId, patientId, date, startTime, endTime) && shiftRepository.existsByDateAfterAndEmployee_IdAndStartTimeBeforeAndEndTimeAfter(date, vet.getEmployee().getId(), startTime, endTime)
                && !vacationRepository.existsByFullTimeEmployee_IdAndStartBetweenAndEndBetween(vet.getEmployee().getId(), date);
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

    private boolean visitExistsByVetPatientDateStartEndTime(Long vetId, Long petId, LocalDate date, LocalTime startTime, LocalTime endTime) {
        return visitRepository.existsByVet_IdAndPatient_IdAndDateAndStartTimeAndEndTime(vetId, petId, date, startTime, endTime);
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
