package services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.TypeOfQuestionRepository;

@Service
@RequiredArgsConstructor
public class TypeOfQuestionService {
    private final TypeOfQuestionRepository typeOfQuestionRepository;

}
