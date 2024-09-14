package com.english.battle.services;

import com.english.battle.repository.CorrectAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CorrectAnswerService {
    private final CorrectAnswerRepository correctAnswerRepository;

}
