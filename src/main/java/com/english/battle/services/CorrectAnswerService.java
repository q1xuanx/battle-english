package com.english.battle.services;

import com.english.battle.dto.response.ApiResponse;
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
    public ApiResponse<CorrectAnswer> GetCorrectAnswer(String idCorrectAnswer){
        try {
            Optional<CorrectAnswer> correct = correctAnswerRepository.findById(idCorrectAnswer);
            return correct.map(correctAnswer -> new ApiResponse<>(200, true, "Get data success", correctAnswer)).orElseGet(() -> new ApiResponse<>(404, false, "Not found correct answer with id: " + idCorrectAnswer, null));
        } catch (Exception e) {
            logger.error("Error find correct answer with id: {} | Error name: {}", idCorrectAnswer, e.getMessage());
            return new ApiResponse<>(400, false, "Error while find correct answer " + e.getCause(), null);
        }
    }
    public ApiResponse<CorrectAnswer> UpdateCorrectAnswer(String idCorrect, String updatedAnswer){
        try {
            Optional<CorrectAnswer> correct = correctAnswerRepository.findById(idCorrect);
            if (correct.isPresent()) {
                CorrectAnswer correctAnswer = correct.get();
                correctAnswer.setCorrectAnswer(updatedAnswer);
                correctAnswerRepository.save(correctAnswer);
                return new ApiResponse<>(200, true, "Update data success", correctAnswer);
            }
            return new ApiResponse<>(404, false , "Not found correct answer with id: " + idCorrect, null);
        }catch (Exception e) {
            logger.error("Error update correct answer with id: {} | Error name: {}", idCorrect, e.getMessage());
            return new ApiResponse<>(400, false, "Error while find correct answer " + e.getCause(), null);
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
