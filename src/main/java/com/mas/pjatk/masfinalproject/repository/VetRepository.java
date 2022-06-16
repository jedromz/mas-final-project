package com.mas.pjatk.masfinalproject.repository;

import com.mas.pjatk.masfinalproject.model.Vet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface VetRepository extends JpaRepository<Vet,Long> {

}
