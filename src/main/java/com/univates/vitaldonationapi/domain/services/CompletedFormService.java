package com.univates.vitaldonationapi.domain.services;

import com.univates.vitaldonationapi.domain.entity.CompletedForm;
import com.univates.vitaldonationapi.domain.exception.model.NotFoundException;
import com.univates.vitaldonationapi.domain.repository.CompletedFormRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CompletedFormService {

    private final CompletedFormRepository completedFormRepository;

    public List<CompletedForm> findAll() {
        return completedFormRepository.findAll();
    }

    public CompletedForm findById(UUID id) {
        return completedFormRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public CompletedForm save(CompletedForm form) {
        return completedFormRepository.save(form);
    }

    public void delete(UUID id) {
        completedFormRepository.delete(findById(id));
    }

    public void delete(CompletedForm form) {
        completedFormRepository.delete(form);
    }

}
