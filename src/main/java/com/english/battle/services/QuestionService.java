package com.english.battle.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.english.battle.repository.QuestionRepository;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
}
