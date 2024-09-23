package com.english.battle.services;


import com.english.battle.models.DetailsRoom;
import com.english.battle.repository.DetailsRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DetailsRoomService {
    private final DetailsRoomRepository detailsRoomRepository;
    public String AddNewDetails(DetailsRoom detailsRoom) {
        try {
            detailsRoomRepository.save(detailsRoom);
            return "Ok Good";
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
