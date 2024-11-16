package com.english.battle.services;

import com.english.battle.dto.request.UserCreateRoomRequest;
import com.english.battle.dto.response.ApiResponse;
import com.english.battle.models.QueueUser;
import com.english.battle.models.User;
import com.english.battle.repository.QueueUserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class QueueUserService {
    private final QueueUserRepository queueUserRepository;
    private final UserService userService;
    private final RoomService roomService;
    private final Logger log = LoggerFactory.getLogger(QueueUserService.class);
    //Search user if queue.length > 1 then create new Room with current user and user in queue.
    public ApiResponse<Object> searchUser(Long idUser, String typeOfRoom) {
        try {
            User user = userService.getUserById(idUser);
            if (user == null) {
                return new ApiResponse<>(404, false, "Not found user", null);
            }
            List<QueueUser> queue = queueUserRepository.findAll();
            if (queue.isEmpty()) {
                QueueUser queueUser = new QueueUser();
                queueUser.setUser(user);
                queueUserRepository.save(queueUser);
                return new ApiResponse<>(200, true, "User is in queue", user);
            }
            QueueUser userInQueue = queueUserRepository.findAll().getFirst();
            if (userInQueue == null) {
                return new ApiResponse<>(404, false, "Not found user", null);
            }
            List<UserCreateRoomRequest> listUser = new ArrayList<>(createListToMakeRoom(userInQueue.getUser().getId(),idUser));
            queueUserRepository.delete(userInQueue);
            return roomService.CreateNewRoom(listUser, typeOfRoom);
        }catch (Exception e){
            log.error("Error when add new user: || error message {}", e.getMessage());
            return new ApiResponse<>(400, false, "Error when add user", null);
        }
    }
    public ApiResponse<Object> leaveQueue(Long idUser) {
        try{
            List<QueueUser> userInQueue = queueUserRepository.findAll();
            Optional<QueueUser> user = userInQueue.stream().filter(s -> Objects.equals(s.getUser().getId(), idUser)).findFirst();
            if (user.isPresent()){
                queueUserRepository.delete(user.get());
                return new ApiResponse<>(200, true, "User left in queue", null);
            }
            return new ApiResponse<>(404, false, "Not found user", null);
        } catch (Exception e){
            log.error("Error when leave user: || error message {}", e.getMessage());
            return new ApiResponse<>(400, false, e.getMessage(), null);
        }
    }
    public List<UserCreateRoomRequest> createListToMakeRoom (Long idUser1, Long idUser2) {
        List<UserCreateRoomRequest> listUser = new ArrayList<>();
        UserCreateRoomRequest user1 = new UserCreateRoomRequest();
        user1.setIdUser(idUser1);
        UserCreateRoomRequest user2 = new UserCreateRoomRequest();
        user2.setIdUser(idUser2);
        listUser.add(user1);
        listUser.add(user2);
        return listUser;
    }
}
