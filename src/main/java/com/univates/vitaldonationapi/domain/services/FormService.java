package com.univates.vitaldonationapi.domain.services;

import com.univates.vitaldonationapi.domain.entity.Form;
import com.univates.vitaldonationapi.domain.exception.model.NotFoundException;
import com.univates.vitaldonationapi.domain.repository.FormRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FormService {

    private final FormRepository formRepository;

    public List<Form> findAll() {
        return formRepository.findAll();
    }

    public Form findById(UUID id) {
        return formRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Form save(Form form) {
        return formRepository.save(form);
    }

    public void delete(UUID id) {
        formRepository.delete(findById(id));
    }

    public void delete(Form form) {
        formRepository.delete(form);
    }

}
