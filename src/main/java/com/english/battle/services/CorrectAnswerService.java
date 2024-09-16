package com.english.battle.services;

import com.english.battle.models.CorrectAnswer;
import com.english.battle.repository.CorrectAnswerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CorrectAnswerService {
    private final CorrectAnswerRepository correctAnswerRepository;
    private final Logger logger = LoggerFactory.getLogger(CorrectAnswerService.class);
    public CorrectAnswer CreateNewCorrectAnswer(String correctAnswer){
        try{
            if (correctAnswer.isEmpty() || correctAnswer.isBlank()){
                throw new InputMismatchException("Correct answer empty or blank");
            }
            CorrectAnswer correct = new CorrectAnswer();
            correct.setCorrectAnswer(correctAnswer);
            correctAnswerRepository.save(correct);
            return correct;
        }catch (Exception e) {
            logger.error("Error while save new entity | Error name: {}", e.getMessage());
            throw new RuntimeException(e.getCause());
        }
    }
    public CorrectAnswer GetCorrectAnswer(String idCorrectAnswer){
        try {
            Optional<CorrectAnswer> correct = correctAnswerRepository.findById(idCorrectAnswer);
            if (correct.isPresent()) return correct.get();
            throw new EntityNotFoundException("Not found correct answer with id: " + idCorrectAnswer);
        } catch (Exception e) {
            logger.error("Error find correct answer with id: {} | Error name: {}", idCorrectAnswer, e.getMessage());
            throw new RuntimeException("Error while find correct answer {}", e.getCause());
        }
    }
    public CorrectAnswer UpdateCorrectAnswer(String idCorrect, String updatedAnswer){
        try {
            Optional<CorrectAnswer> correct = correctAnswerRepository.findById(idCorrect);
            if (correct.isPresent()) {
                CorrectAnswer correctAnswer = correct.get();
                correctAnswer.setCorrectAnswer(updatedAnswer);
                correctAnswerRepository.save(correctAnswer);
                return correctAnswer;
            }
            throw new EntityNotFoundException("Correct answer not found with id: " + idCorrect);
        }catch (Exception e) {
            logger.error("Error update correct answer with id: {} | Error name: {}", idCorrect, e.getMessage());
            throw new RuntimeException("Fail to update ", e.getCause());
        }
    }
    public boolean DeleteCorrectAnswer(String idCorrectAnswer){
        try {
            Optional<CorrectAnswer> correct = correctAnswerRepository.findById(idCorrectAnswer);
            if (correct.isPresent()) {
                CorrectAnswer correctAnswer = correct.get();
                correctAnswer.setIsHide(true);
                correctAnswerRepository.save(correctAnswer);
                return true;
            }
            throw new EntityNotFoundException("Correct answer not found with id: " + idCorrectAnswer);
        } catch (Exception e) {
            logger.error("Error delete correct answer with id: {} | Error name: {}", idCorrectAnswer, e.getMessage());
            throw new RuntimeException("Fail to delete ", e.getCause());
        }
    }
}
