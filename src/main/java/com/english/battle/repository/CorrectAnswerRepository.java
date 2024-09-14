package com.english.battle.repository;

import com.english.battle.models.CorrectAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorrectAnswerRepository extends JpaRepository<CorrectAnswer, String> {

}
