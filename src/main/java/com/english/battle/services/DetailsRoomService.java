package com.english.battle.services;


import com.english.battle.models.*;
import com.english.battle.repository.DetailsRoomRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DetailsRoomService {
    private final DetailsRoomRepository detailsRoomRepository;
    private final QuestionService questionService;
    private final Logger logger = LoggerFactory.getLogger(DetailsRoomService.class);
    public String AddNewDetails(DetailsRoom detailsRoom) {
        try {
            detailsRoomRepository.save(detailsRoom);
            return "Ok Good";
        }catch (Exception e){
            return e.getMessage();
        }
    }
    public void checkSubmitUser(User user, Room room){
        List<DetailsRoom> list = detailsRoomRepository.findDetailsRoomByIdRoom(room);
        boolean isSubmit = list.stream().anyMatch(s-> s.getUserSubmit().equals(user) && s.getIdRoom().equals(room));
        if (!isSubmit){
            DetailsRoom detailsRoom = new DetailsRoom();
            detailsRoom.setUserSubmit(user);
            detailsRoom.setSoccerOfUser(0);
            detailsRoom.setIdRoom(room);
            LocalDateTime now = LocalDateTime.now();
            detailsRoom.setTimeSubmit(now);
            List<QuestionAfterCheck> listQuest = questionService.CheckCorrectQuestionOfUser(room.getListQuestions());
            detailsRoom.setQuestionAfterCheckList(listQuest);
            detailsRoomRepository.save(detailsRoom);
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
