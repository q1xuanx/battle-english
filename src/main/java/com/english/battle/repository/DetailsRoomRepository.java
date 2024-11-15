package com.english.battle.repository;

import com.english.battle.models.DetailsRoom;
import com.english.battle.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetailsRoomRepository extends JpaRepository<DetailsRoom, String> {
    List<DetailsRoom> findDetailsRoomByIdRoom(Room room);
}
