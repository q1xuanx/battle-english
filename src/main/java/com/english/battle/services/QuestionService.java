package com.english.battle.services;

import com.english.battle.dto.response.ApiResponse;
import com.english.battle.models.CorrectAnswer;
import com.english.battle.models.Questions;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.english.battle.repository.QuestionRepository;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final CorrectAnswerService correctAnswerService;
    private final Logger logger = LoggerFactory.getLogger(QuestionService.class);

    public ApiResponse<Object> CreateQuestion(List<Questions> questions) {
        try{
            for(Questions quest : questions) {
                List<String> list = new ArrayList<>();
                list.add(quest.getAnswerA());
                list.add(quest.getAnswerB());
                list.add(quest.getAnswerC());
                list.add(quest.getAnswerD());
                Optional<String> findCorrectAnswer = list.stream().filter(s -> s.contains("||CorrectAnswer")).findFirst();
                if (findCorrectAnswer.isPresent()) {
                    String correctAnswer = findCorrectAnswer.get().split("\\|\\|")[0];
                    CorrectAnswer isAdded = correctAnswerService.CreateNewCorrectAnswer(correctAnswer);
                    quest.setAnswerCorrect(isAdded);
                }else {
                    return new ApiResponse<>(404, "Fail to create", "Correct answer doesn't appear in question");
                }
            }
            return new ApiResponse<>(200, questionRepository.saveAll(questions), "Add list question success");
       } catch (Exception e) {
            logger.error("Error while creating new question {}", e.getMessage());
            return new ApiResponse<>(400, "Fail to create", e.getMessage());
        }
    }
}
