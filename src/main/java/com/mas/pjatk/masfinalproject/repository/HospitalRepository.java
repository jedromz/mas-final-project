package com.mas.pjatk.masfinalproject.repository;

import com.mas.pjatk.masfinalproject.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    @Query("select h from Hospital h left join fetch h.rooms where h.id = ?1")
    Optional<Hospital> findHospitalByIdWithRooms(Long id);
}
