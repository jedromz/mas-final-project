package com.mas.pjatk.masfinalproject.repository;

import com.mas.pjatk.masfinalproject.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {
}
