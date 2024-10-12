package com.english.battle.services;

import com.english.battle.dto.request.UserCreateRoomRequest;
import com.english.battle.dto.response.ApiResponse;
import com.english.battle.models.*;
import com.english.battle.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final DetailsRoomService detailsRoomService;
    private final QuestionService questions;
    private final UserService users;
    private final Logger logger = LoggerFactory.getLogger(RoomService.class);
    public ApiResponse<Object> CreateNewRoom(List<UserCreateRoomRequest> usersId, String typeOfQuestion) {
        try{
            Room room = new Room();
            List<User> users = new ArrayList<>();
            for(UserCreateRoomRequest userRequest : usersId) {
                User getUser = this.users.getUserById(userRequest.getIdUser());
                if (getUser == null){
                    return new ApiResponse<>(404, false, "Not found user with id: " + userRequest.getIdUser(), null);
                }
                users.add(getUser);
            }
            room.setMaxUserInRoom(users.size());
            room.setStatusRoom("Progress");
            room.setCurrentUser(users);
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime end = LocalDateTime.now().plusMinutes(30);
            room.setStartTime(now);
            room.setEndTime(end);
            List<Questions> mixQuest = questions.MixQuestion(typeOfQuestion, 5);
            System.out.println(mixQuest.size());
            if (!mixQuest.isEmpty()){
                room.setListQuestions(mixQuest);
                roomRepository.save(room);
                return new ApiResponse<>(200, true, "Room created", room);
            }else {
                return new ApiResponse<>(400, false, "Fail when make list question", null);
            }
        }catch (Exception e){
            logger.error("Error when create new room: || message {}",e.getMessage());
            return new ApiResponse<>(400, false, e.getLocalizedMessage(), null);
        }
    }
    public ApiResponse<Object> CalculatePoint(List<Questions> questions, Long idUser, String idRoom) {
        try {
            LocalDateTime now = LocalDateTime.now();
            boolean isSubmit = detailsRoomService.IsSubmit(idUser, idRoom);
            Optional<Room> outOfDate = roomRepository.findById(idRoom);
            if (outOfDate.isEmpty()){
                return new ApiResponse<>(404, false, "Not found room with id: " + idRoom, idRoom);
            }
            LocalDateTime getTime = outOfDate.get().getEndTime();
            if (now.isAfter(getTime)) {
                return new ApiResponse<>(400, false, "Out of time to submit, the contest end in " + getTime, now);
            }
            if (isSubmit){
                return new ApiResponse<>(400, false, "You have submitted yet", null);
            }
            DetailsRoom details = new DetailsRoom();
            List<QuestionAfterCheck> listCheckQuest = this.questions.CheckCorrectQuestionOfUser(questions);
            int pointAfterSubmit = listCheckQuest.stream().mapToInt(QuestionAfterCheck::getPointGet).sum();
            LocalDateTime submit = LocalDateTime.now();
            details.setIdRoom(outOfDate.get());
            details.setTimeSubmit(submit);
            details.setUserSubmit(users.getUserById(idUser));
            details.setQuestionAfterCheckList(listCheckQuest);
            details.setSoccerOfUser(pointAfterSubmit);
            String isAdded = detailsRoomService.AddNewDetails(details);
            if ("Ok Good".equals(isAdded)){
                return new ApiResponse<>(200, true, "Submit success and get result", details);
            }
            return new ApiResponse<>(400, false, "Submit false please try again", "Error message: " + isAdded);
        }catch (Exception e){
            logger.error("Error when calculate point: {}", e.getMessage());
            return new ApiResponse<>(400, false, e.getMessage(), null);
        }
    }
    public ApiResponse<Object> UpdateStatusOfRoom(String idRoom){
        try {
            Optional<Room> room = roomRepository.findById(idRoom);
            return room.map(r -> {
                r.setStatusRoom("Complete");
                roomRepository.save(r);
                return new ApiResponse<Object>(200, true, "Room updated", r);
            }).orElseGet(() -> new ApiResponse<>(404, false, "Not found room with id " + idRoom, null));
        }catch (Exception e){
            logger.error("Error when update: {}", e.getMessage());
            return new ApiResponse<>(400, false, e.getMessage(), null);
        }
    }
    public ApiResponse<Object> CalculateRank (String idRoom){
        try{
            Optional<Room> room = roomRepository.findById(idRoom);
            if (room.isPresent()){
                List<DetailsRoom> listDetail = detailsRoomService.GetAllRankOfUser(room.get());
                if (listDetail == null){
                    return new ApiResponse<>(400, false, "Can't not calculate rank of room with id: " + idRoom, null);
                }
                return new ApiResponse<>(200, true, "Get success", listDetail);
            }else {
                return new ApiResponse<>(404, false, "Not found room with id " + idRoom, null);
            }
        }catch (Exception e){
            logger.error("Error when calculate rank: {}", e.getMessage());
            return new ApiResponse<>(400, false, e.getMessage(), null);
        }
    }
}
