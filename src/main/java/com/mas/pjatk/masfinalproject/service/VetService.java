package com.mas.pjatk.masfinalproject.service;

import com.mas.pjatk.masfinalproject.error.EntityNotFoundException;
import com.mas.pjatk.masfinalproject.model.Patient;
import com.mas.pjatk.masfinalproject.model.Vet;
import com.mas.pjatk.masfinalproject.repository.VetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VetService {

    private final VetRepository vetRepository;

    /**
     * return vet with Visits
     * @param id vetId
     * @return found Vet
     * @throws EntityNotFoundException
     */
    public Vet findVetWithVisits(Long id) throws EntityNotFoundException {
        return vetRepository.findVetWithVisits(id)
                .orElseThrow(() -> new EntityNotFoundException("VET", id.toString()));
    }

    public List<Vet> findAll() {
        return vetRepository.findAll();
    }

}
