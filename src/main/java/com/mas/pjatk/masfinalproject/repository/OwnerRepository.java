package com.mas.pjatk.masfinalproject.repository;

import com.mas.pjatk.masfinalproject.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner,Long> {
    Optional<Owner> findByEmail(String email);

}
