package com.english.battle.repository;

import com.english.battle.models.TypeOfQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeOfQuestionRepository extends JpaRepository<TypeOfQuestion, String> {
}
