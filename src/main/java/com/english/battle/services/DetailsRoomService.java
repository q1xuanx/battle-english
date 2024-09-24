package com.english.battle.services;


import com.english.battle.models.DetailsRoom;
import com.english.battle.models.Room;
import com.english.battle.repository.DetailsRoomRepository;
import com.english.battle.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DetailsRoomService {
    private final DetailsRoomRepository detailsRoomRepository;
    private final RoomRepository roomRepository;
    private final Logger logger = LoggerFactory.getLogger(DetailsRoomService.class);
    public String AddNewDetails(DetailsRoom detailsRoom) {
        try {
            detailsRoomRepository.save(detailsRoom);
            return "Ok Good";
        }catch (Exception e){
            return e.getMessage();
        }
    }
    public List<DetailsRoom> GetAllRankOfUser(Room room){
        try{
            List<DetailsRoom> listDetails = detailsRoomRepository.findAll();
            listDetails.stream().filter(s -> s.getIdRoom().getIdRoom().equals(room.getIdRoom())).sorted(Comparator.comparing(DetailsRoom::getSoccerOfUser).reversed());
            return listDetails;
        }catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
    public boolean IsSubmit (Long idUser, String idRoom){
        List<DetailsRoom> listDetail = detailsRoomRepository.findAll();
        Optional<DetailsRoom> detail = listDetail.stream().filter(s -> s.getIdRoom().getIdRoom().equals(idRoom) && s.getUserSubmit().getId().equals(idUser)).findFirst();
        return detail.isPresent();
    }
}
