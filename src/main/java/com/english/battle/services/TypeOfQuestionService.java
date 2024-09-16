package com.english.battle.services;

import com.english.battle.dto.response.ApiResponse;
import com.english.battle.models.CorrectAnswer;
import com.english.battle.models.TypeOfQuestion;
import com.english.battle.repository.TypeOfQuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TypeOfQuestionService {
    private final TypeOfQuestionRepository typeOfQuestionRepository;
    private final Logger logger = LoggerFactory.getLogger(TypeOfQuestionService.class);

    public ApiResponse<Object> CreateNewTypeQuestion(String typeName){
        try{
            if (typeName.isEmpty() || typeName.isBlank()){
                return new ApiResponse<>(404, null, "Can't find type name");
            }
            TypeOfQuestion typeOf = new TypeOfQuestion();
            typeOf.setNameType(typeName);
            typeOfQuestionRepository.save(typeOf);
            return new ApiResponse<>(200, typeOf, "Create success");
        }catch (Exception e) {
            logger.error("Error while save new entity | Error name: {}", e.getMessage());
            return new ApiResponse<>(400, null , e.getMessage());
        }
    }
    public ApiResponse<Object> GetTypeOfQuestion(String idType){
        try {
            Optional<TypeOfQuestion> type = typeOfQuestionRepository.findById(idType);
            return type.<ApiResponse<Object>>map(typeOfQuestion -> new ApiResponse<>(200, typeOfQuestion, "Create success")).orElseGet(() -> new ApiResponse<>(404, null, "Not found type of question with id: " + idType));
        } catch (Exception e) {
            logger.error("Error find type of question with id: {} | Error name: {}", idType, e.getMessage());
            return new ApiResponse<>(400, null , e.getMessage());
        }
    }
    public ApiResponse<Object> UpdateTypeQuestion(String idType, String updateNameType){
        try {
            Optional<TypeOfQuestion> type = typeOfQuestionRepository.findById(idType);
            if (type.isPresent()) {
                TypeOfQuestion typeQuestion = type.get();
                typeQuestion.setNameType(updateNameType);
                typeOfQuestionRepository.save(typeQuestion);
                return new ApiResponse<>(200, typeQuestion, "Update success");
            }
            return new ApiResponse<>(404, null, "Not found type of question with id: " + idType);
        }catch (Exception e) {
            logger.error("Error update type question with id: {} | Error name: {}", idType, e.getMessage());
            return new ApiResponse<>(400, null , e.getMessage());
        }
    }
    public ApiResponse<Object> DeleteCorrectAnswer(String idTye){
        try {
            Optional<TypeOfQuestion> type = typeOfQuestionRepository.findById(idTye);
            if (type.isPresent()) {
                TypeOfQuestion typeOfQuestion = type.get();
                typeOfQuestion.setIsHide(true);
                typeOfQuestionRepository.save(typeOfQuestion);
                return new ApiResponse<>(200, null, "Delete success");
            }
            return new ApiResponse<>(404, null, "Not found type of question with id: " + idTye);
         } catch (Exception e) {
            logger.error("Error delete type of question with id: {} | Error name: {}", idTye, e.getMessage());
            return new ApiResponse<>(400, null , e.getMessage());
        }
    }
}
