package com.mas.pjatk.masfinalproject.service;

import com.mas.pjatk.masfinalproject.error.EntityNotFoundException;
import com.mas.pjatk.masfinalproject.model.Owner;
import com.mas.pjatk.masfinalproject.model.Patient;
import com.mas.pjatk.masfinalproject.repository.OwnerRepository;
import com.mas.pjatk.masfinalproject.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public Owner getPatient(Long id) throws EntityNotFoundException {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("OWNER", id.toString()));
    }

    public Page<Owner> getAllPatients(Pageable pageable) {
        return ownerRepository.findAll(pageable);
    }

    public Owner savePatient(Owner owner) {
        return ownerRepository.saveAndFlush(owner);
    }

    @Transactional
    public void softDeleteById(Long id) throws EntityNotFoundException {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("OWNER", id.toString()));
        owner.setDeleted(true);
    }
}
