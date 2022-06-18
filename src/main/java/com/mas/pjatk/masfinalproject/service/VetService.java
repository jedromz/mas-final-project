package com.mas.pjatk.masfinalproject.service;

import com.mas.pjatk.masfinalproject.error.EntityNotFoundException;
import com.mas.pjatk.masfinalproject.model.Shift;
import com.mas.pjatk.masfinalproject.model.Vet;
import com.mas.pjatk.masfinalproject.model.Visit;
import com.mas.pjatk.masfinalproject.repository.VetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class VetService {

    private final VetRepository vetRepository;

    public Vet getVet(Long id) throws EntityNotFoundException {
        return vetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("VET", id.toString()));
    }

    public Vet findVetWithVisits(Long id) throws EntityNotFoundException {
        return vetRepository.findVetWithVisits(id)
                .orElseThrow(() -> new EntityNotFoundException("VET", id.toString()));
    }


    public Page<Vet> getAllVets(Pageable pageable) {
        return vetRepository.findAll(pageable);
    }

    public Vet saveVet(Vet vet) {
        return vetRepository.saveAndFlush(vet);
    }

    @Transactional
    public void softDeleteById(Long id) throws EntityNotFoundException {
        Vet vet = vetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("VET", id.toString()));
        vet.setDeleted(true);
    }

    public Set<Visit> getVisitsById(Long id) throws EntityNotFoundException {
        Vet vet = vetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("VET", id.toString()));
        return vet.getVisits();
    }

}
