package services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.QuestionRepository;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
}
