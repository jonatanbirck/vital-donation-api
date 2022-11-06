package com.univates.vitaldonationapi.domain.services;

import com.univates.vitaldonationapi.domain.entity.CompletedQuestion;
import com.univates.vitaldonationapi.domain.exception.model.AlreadyExistsException;
import com.univates.vitaldonationapi.domain.exception.model.NotFoundException;
import com.univates.vitaldonationapi.domain.repository.CompletedQuestionRepository;
import com.univates.vitaldonationapi.helper.PropertyHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CompletedQuestionService {

    private final CompletedQuestionRepository completedQuestionRepository;

    public List<CompletedQuestion> findAll() {
        return completedQuestionRepository.findAll();
    }

    public CompletedQuestion findById(UUID id) {
        return completedQuestionRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public CompletedQuestion create(CompletedQuestion question) {
        verifyIfExists(question);
        return completedQuestionRepository.save(question);
    }

    public CompletedQuestion update(CompletedQuestion inQuestion) {
        var question = findById(inQuestion.getId());
        PropertyHelper.copy(inQuestion, question);
        return completedQuestionRepository.save(question);
    }

    public void delete(UUID id) {
        completedQuestionRepository.delete(findById(id));
    }

    public void delete(CompletedQuestion question) {
        completedQuestionRepository.delete(question);
    }

    private void verifyIfExists(CompletedQuestion inQuestion) {
        completedQuestionRepository.findByQuestionIdAndCompletedFormId(inQuestion.getQuestion().getId(), inQuestion.getCompletedForm().getId())
                .ifPresent(question -> {throw new AlreadyExistsException(question.getId());});
    }

}
