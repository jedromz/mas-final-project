package com.mas.pjatk.masfinalproject.repository;

import com.mas.pjatk.masfinalproject.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {

    List<Visit> findByVet_Employee_IdAndDate(Long id, LocalDate date);

    List<Visit> findByVet_IdAndDate(Long id, LocalDate date);

    List<Visit> findByPatient_IdAndDate(Long id, LocalDate date);

    boolean existsByVet_IdAndPatient_IdAndDateAndStartTimeAndEndTime(Long id, Long id1, LocalDate date, LocalTime startTime, LocalTime endTime);


}
