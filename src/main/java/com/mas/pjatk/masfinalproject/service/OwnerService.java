package com.mas.pjatk.masfinalproject.service;

import com.mas.pjatk.masfinalproject.error.EntityNotFoundException;
import com.mas.pjatk.masfinalproject.model.Owner;
import com.mas.pjatk.masfinalproject.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    /**
     * @param owner
     * @return saved Owner
     */
    public Owner saveOwner(Owner owner) {
        return ownerRepository.saveAndFlush(owner);
    }

    /**
     * @param email owner email
     * @return found email
     * @throws EntityNotFoundException
     */
    public Owner findByEmail(String email) throws EntityNotFoundException {
        return ownerRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("OWNER_EMAIL", email));
    }
}
