package com.mas.pjatk.masfinalproject.repository;

import com.mas.pjatk.masfinalproject.model.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface VacationRepository extends JpaRepository<Vacation,Long> {
    @Query("""
            select (count(v) > 0) from Vacation v
            where v.fullTimeEmployee.id = ?1 and ?2 between v.start and v.end""")
    boolean existsByFullTimeEmployee_IdAndStartBetweenAndEndBetween(Long id, LocalDate startStart);



}
