package com.mas.pjatk.masfinalproject.repository;

import com.mas.pjatk.masfinalproject.model.Vet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface VetRepository extends JpaRepository<Vet, Long> {
    List<Vet> findByVisits_Date(LocalDate date);

    @Query("select v from Vet v left join fetch v.visits where v.id = ?1")
    Optional<Vet> findVetWithVisits(Long id);

    List<Vet> findByVisits_DateAndVisits_StartTimeBetweenAndVisits_EndTimeBetween(LocalDate date, LocalTime startTimeStart, LocalTime startTimeEnd, LocalTime endTimeStart, LocalTime endTimeEnd);

    @Query("""
            select distinct ve from Vet ve where ve.id not in(select distinct v from Vet v inner join v.visits visits
            where visits.date = ?1 and  ?2  between visits.startTime and visits.endTime and  ?3 between visits.startTime  and visits.endTime)""")
    List<Vet> findByVisits_DateAndVisits_StartTimeIsBetweenAndVisits_EndTimeIsBetween(LocalDate date, LocalTime startTime, LocalTime endTime);
}
