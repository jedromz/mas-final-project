package com.mas.pjatk.masfinalproject.service;

import com.mas.pjatk.masfinalproject.model.Owner;
import com.mas.pjatk.masfinalproject.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public Owner savePatient(Owner owner) {
        return ownerRepository.saveAndFlush(owner);
    }
}
