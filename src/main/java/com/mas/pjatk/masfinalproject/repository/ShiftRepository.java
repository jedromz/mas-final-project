package com.mas.pjatk.masfinalproject.repository;

import com.mas.pjatk.masfinalproject.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public interface ShiftRepository extends JpaRepository<Shift,Long> {

    @Query("""
            select (count(s) > 0) from Shift s
            where s.date > ?1 and s.employee.id = ?2 and s.startTime < ?3 and s.endTime > ?4""")
    boolean existsByDateAfterAndEmployee_IdAndStartTimeBeforeAndEndTimeAfter(LocalDate date, Long id, LocalTime startTime, LocalTime endTime);


}
