package com.mas.pjatk.masfinalproject.repository;

import com.mas.pjatk.masfinalproject.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("select p from Patient p left join fetch p.visits where p.id = ?1")
    Optional<Patient> findByIdWithVisits(Long id);

}
