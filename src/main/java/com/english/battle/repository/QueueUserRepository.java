package com.english.battle.repository;

import com.english.battle.models.QueueUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueueUserRepository extends JpaRepository<QueueUser, String> {
}
