package com.english.battle.repository;

import com.english.battle.models.DetailsRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetailsRoomRepository extends JpaRepository<DetailsRoom, String> {
    List<DetailsRoom> findDetailsRoomByIdRoom(String idRoom);
}
