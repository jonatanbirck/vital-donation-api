package com.univates.vitaldonationapi.domain.services;

import com.univates.vitaldonationapi.domain.entity.Answer;
import com.univates.vitaldonationapi.domain.exception.model.AlreadyExistsException;
import com.univates.vitaldonationapi.domain.exception.model.NotFoundException;
import com.univates.vitaldonationapi.domain.repository.AnswerRepository;
import com.univates.vitaldonationapi.helper.PropertyHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public List<Answer> findAll() {
        return answerRepository.findAll();
    }

    public Answer findById(UUID id) {
        return answerRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Answer create(Answer answer) {
        verifyIfExists(answer);
        return answerRepository.save(answer);
    }

    public Answer update(Answer inAnswer) {
        var answer = findById(inAnswer.getId());
        PropertyHelper.copy(inAnswer, answer);
        return answerRepository.save(answer);
    }

    public void delete(UUID id) {
        answerRepository.delete(findById(id));
    }

    public void delete(Answer answer) {
        answerRepository.delete(answer);
    }

    private void verifyIfExists(Answer inAnswer) {
        answerRepository.findByAnswerAndQuestionId(inAnswer.getAnswer(), inAnswer.getQuestion().getId())
                .ifPresent(answer -> {throw new AlreadyExistsException(answer.getId());});
    }

}
