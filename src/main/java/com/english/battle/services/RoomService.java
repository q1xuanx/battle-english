package com.english.battle.services;

import com.english.battle.dto.response.ApiResponse;
import com.english.battle.models.*;
import com.english.battle.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public ApiResponse<Object> CreateNewRoom(List<User> users, String typeOfQuestion) {
        try{
            Room room = new Room();
            room.setMaxUserInRoom(users.size());
            room.setStatusRoom("Progress");
            room.setCurrentUser(users);
            ApiResponse<Object> res = questions.MakeListQuest(30,typeOfQuestion);
            if (res.getCode() == 200){
                List<Questions> listQuest = (List<Questions>) res.getData();
                room.setListQuestions(listQuest);
                roomRepository.save(room);
                return new ApiResponse<>(200, true, "Room created", room);
            }else {
                return new ApiResponse<>(400, false, "Fail when make list question", null);
            }
        }catch (Exception e){
            logger.error("Error when create new room: {}", e.getMessage());
            return new ApiResponse<>(400, false, e.getMessage(), null);
        }
    }
    public ApiResponse<Object> CalculatePoint(List<Questions> questions, Long idUser, String idRoom) {
        try{
            DetailsRoom details = new DetailsRoom();
            List<QuestionAfterCheck> listCheckQuest = this.questions.CheckCorrectQuestionOfUser(questions);
            int pointAfterSubmit = listCheckQuest.stream().mapToInt(QuestionAfterCheck::getPointGet).sum();
            LocalDateTime submit = LocalDateTime.now();
            details.setTimeSubmit(submit);
            details.setUserSubmit(users.getUserById(idUser));
            details.setQuestionAfterCheckList(listCheckQuest);
            details.setSoccerOfUser(pointAfterSubmit);
            String isAdded = detailsRoomService.AddNewDetails(details);
            if (isAdded.equals("Ok Good")){
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
}
