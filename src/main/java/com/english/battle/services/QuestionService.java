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

import java.security.SecureRandom;
import java.util.*;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final CorrectAnswerService correctAnswerService;
    private final Logger logger = LoggerFactory.getLogger(QuestionService.class);
    public ApiResponse<Object> CreateQuestion(List<Questions> questions) {
        try{
            for(Questions quest : questions) {
                String validationMessage = ValidateQuestionInput(quest);
                if (!validationMessage.equals("Good")){
                    return new ApiResponse<>(400, false, validationMessage, null);
                }
                List<String> list = new ArrayList<>();
                list.add(quest.getAnswerA());
                list.add(quest.getAnswerB());
                list.add(quest.getAnswerC());
                list.add(quest.getAnswerD());
                Optional<String> findCorrectAnswer = list.stream().filter(s -> s.contains("||CorrectAnswer")).findFirst();
                if (findCorrectAnswer.isPresent()) {
                    String rmvSuffix = RemoveCorrectAnswerSuffix(quest, findCorrectAnswer.get());
                    CorrectAnswer isAdded = correctAnswerService.CreateNewCorrectAnswer(rmvSuffix);
                    quest.setAnswerCorrect(isAdded);
                }else {
                    return new ApiResponse<>(404, false, "Correct answer doesn't appear in question", null);
                }
            }
            return new ApiResponse<>(200, true, "Add list question success", questionRepository.saveAll(questions));
       } catch (Exception e) {
            logger.error("Error while creating new question {}", e.getMessage());
            return new ApiResponse<>(400, false, e.getMessage(), null);
        }
    }
    public String ValidateQuestionInput(Questions quest){
        if (quest.getDetailQuestion().isEmpty()){
            return "Details question missed";
        }else if (quest.getAnswerA().isEmpty() || quest.getAnswerB().isEmpty() || quest.getAnswerC().isEmpty() || quest.getAnswerD().isEmpty()){
            return "Answer does not exist";
        }
        return "Good";
    }
    public String RemoveCorrectAnswerSuffix(Questions quest, String findCorrectAnswer) {
        String rmvSuffix = findCorrectAnswer.split("\\|\\|")[0];
        if (quest.getAnswerA().equals(findCorrectAnswer)) {
            quest.setAnswerA(rmvSuffix);
        }else if (quest.getAnswerB().equals(findCorrectAnswer)) {
            quest.setAnswerB(rmvSuffix);
        }else if (quest.getAnswerC().equals(findCorrectAnswer)) {
            quest.setAnswerC(rmvSuffix);
        }else if (quest.getAnswerD().equals(findCorrectAnswer)) {
            quest.setAnswerD(rmvSuffix);
        }
        return rmvSuffix;
    }
    public ApiResponse<Object> MakeListQuest(int sizeOfList, String nameType){
        List<Questions> getList = questionRepository.findAll().stream().filter(s-> s.getTypeOfQuestion().getNameType().equals(nameType)).toList();
        if (getList.size() < sizeOfList){
            return new ApiResponse<>(400, false, "Size of list is too big " + sizeOfList, null);
        }
        List<Questions> copyOfList = new ArrayList<>();
        SecureRandom rnd = new SecureRandom();
        for(int i = 0; i < sizeOfList; i++){
            copyOfList.add(getList.remove(rnd.nextInt(getList.size())));
        }
        return new ApiResponse<>(200, true , "Make list quest success", copyOfList);
    }
}
