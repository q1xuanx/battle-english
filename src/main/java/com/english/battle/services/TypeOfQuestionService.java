package com.english.battle.services;

import com.english.battle.repository.TypeOfQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TypeOfQuestionService {
    private final TypeOfQuestionRepository typeOfQuestionRepository;

}
