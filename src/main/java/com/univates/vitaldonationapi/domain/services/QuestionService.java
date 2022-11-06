package com.univates.vitaldonationapi.domain.services;

import com.univates.vitaldonationapi.domain.entity.Question;
import com.univates.vitaldonationapi.domain.exception.model.AlreadyExistsException;
import com.univates.vitaldonationapi.domain.exception.model.NotFoundException;
import com.univates.vitaldonationapi.domain.repository.QuestionRepository;
import com.univates.vitaldonationapi.helper.PropertyHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerService answerService;

    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    public Question findById(UUID id) {
        return questionRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Question findByQuestion(String question) {
        return questionRepository.findByQuestion(question).orElseThrow(NotFoundException::new);
    }

    public Question create(Question question) {
        verifyIfExists(question);
        return questionRepository.save(question);
    }

    public Question update(Question inQuestion) {
        var question = findById(inQuestion.getId());
        PropertyHelper.copy(inQuestion, question);
        return questionRepository.save(question);
    }

    public void delete(UUID id) {
        questionRepository.delete(findById(id));
    }

    public void delete(Question question) {
        questionRepository.delete(question);
    }

    private void verifyIfExists(Question inQuestion) {
        questionRepository.findByQuestion(inQuestion.getQuestion())
                .ifPresent(question -> {throw new AlreadyExistsException(question.getId());});
    }

}
