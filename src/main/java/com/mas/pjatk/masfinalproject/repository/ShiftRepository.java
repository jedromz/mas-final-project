package com.mas.pjatk.masfinalproject.repository;

import com.mas.pjatk.masfinalproject.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface ShiftRepository extends JpaRepository<Shift,Long> {

    Optional<Shift> findByEmployee_IdAndDate(Long id, LocalDate date);

}
