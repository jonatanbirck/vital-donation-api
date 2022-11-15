package com.univates.vitaldonationapi.domain.services;

import com.univates.vitaldonationapi.domain.entity.BloodBag;
import com.univates.vitaldonationapi.domain.exception.model.AlreadyExistsException;
import com.univates.vitaldonationapi.domain.exception.model.NotFoundException;
import com.univates.vitaldonationapi.domain.repository.BloodBagRepository;
import com.univates.vitaldonationapi.helper.PropertyHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BloodBagService {

    private final BloodBagRepository bloodBagRepository;

    public BloodBag findById(UUID id) {
        return bloodBagRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public List<BloodBag> findAll() {
        return bloodBagRepository.findAll();
    }

    public BloodBag create(BloodBag bloodBag) {
        verifyIfExists(bloodBag);
        return bloodBagRepository.save(bloodBag);
    }

    public BloodBag update(BloodBag inBloodBag) {
        var bloodBag = findById(inBloodBag.getId());
        PropertyHelper.copy(inBloodBag, bloodBag);
        return bloodBagRepository.save(bloodBag);
    }

    public void delete(UUID id) {
        bloodBagRepository.delete(findById(id));
    }

    public void delete(BloodBag bloodBag) {
        bloodBagRepository.delete(bloodBag);
    }

    private void verifyIfExists(BloodBag inBloodBag) {
        bloodBagRepository.findByCode(inBloodBag.getCode())
                .ifPresent(b -> {throw new AlreadyExistsException();});
    }

}
