package com.mas.pjatk.masfinalproject.service;

import com.mas.pjatk.masfinalproject.error.EntityNotFoundException;
import com.mas.pjatk.masfinalproject.model.Vet;
import com.mas.pjatk.masfinalproject.repository.VetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VetService {

    private final VetRepository vetRepository;

    public Vet findVetWithVisits(Long id) throws EntityNotFoundException {
        return vetRepository.findVetWithVisits(id)
                .orElseThrow(() -> new EntityNotFoundException("VET", id.toString()));
    }
}
