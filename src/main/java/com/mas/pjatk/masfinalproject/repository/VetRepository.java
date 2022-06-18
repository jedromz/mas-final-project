package com.mas.pjatk.masfinalproject.repository;

import com.mas.pjatk.masfinalproject.model.Vet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VetRepository extends JpaRepository<Vet, Long> {

    @Query("select v from Vet v left join fetch v.visits where v.id = ?1")
    Optional<Vet> findVetWithVisits(Long id);

}
