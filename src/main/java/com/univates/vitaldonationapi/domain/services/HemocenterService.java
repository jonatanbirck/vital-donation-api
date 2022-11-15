package com.univates.vitaldonationapi.domain.services;

import com.univates.vitaldonationapi.domain.entity.Hemocenter;
import com.univates.vitaldonationapi.domain.exception.model.AlreadyExistsException;
import com.univates.vitaldonationapi.domain.exception.model.NotFoundException;
import com.univates.vitaldonationapi.domain.repository.HemocenterRepository;
import com.univates.vitaldonationapi.helper.PropertyHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class HemocenterService {

    private final HemocenterRepository hemocenterRepository;

    public Hemocenter findById(UUID id) {
        return hemocenterRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public List<Hemocenter> findAll() {
        return hemocenterRepository.findAll();
    }

    public Hemocenter create(Hemocenter hemocenter) {
        verifyIfExists(hemocenter);
        return hemocenterRepository.save(hemocenter);
    }

    public Hemocenter update(Hemocenter inHemocenter) {
        var hemocenter = findById(inHemocenter.getId());
        PropertyHelper.copy(inHemocenter, hemocenter);
        return hemocenterRepository.save(hemocenter);
    }

    public void delete(UUID id) {
        hemocenterRepository.delete(findById(id));
    }

    public void delete(Hemocenter hemocenter) {
        hemocenterRepository.delete(hemocenter);
    }

    private void verifyIfExists(Hemocenter hemocenter) {
        hemocenterRepository.findByName(hemocenter.getName())
                .ifPresent(h -> {throw new AlreadyExistsException();});
    }

}
