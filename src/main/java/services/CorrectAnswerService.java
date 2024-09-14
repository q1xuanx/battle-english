package services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.CorrectAnswerRepository;

@Service
@RequiredArgsConstructor
public class CorrectAnswerService {
    private final CorrectAnswerRepository correctAnswerRepository;

}
