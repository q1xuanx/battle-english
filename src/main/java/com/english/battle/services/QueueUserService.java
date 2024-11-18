package com.english.battle.services;

import com.english.battle.dto.request.UserCreateRoomRequest;
import com.english.battle.dto.response.ApiResponse;
import com.english.battle.models.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class QueueUserService {
    private final UserService userService;
    private final RoomService roomService;
    private final RedisTemplate<String, Long> redisTemplate;
    private final Logger log = LoggerFactory.getLogger(QueueUserService.class);
    private final String key = "QueueUser";
    public ApiResponse<Object> searchUser(Long idUser, String typeOfRoom) {
        try {
            User user = userService.getUserById(idUser);
            if (user == null) {
                return new ApiResponse<>(404, false, "Not found user", null);
            }
            ListOperations<String, Long> queue = redisTemplate.opsForList();
            Long queueSize = queue.size(key);
            if (queueSize == null || queueSize == 0) {
                queue.rightPush(key, idUser);
                return new ApiResponse<>(200, true, "User is in queue", user);
            }
            Long userInQueue = queue.leftPop(key);
            List<UserCreateRoomRequest> listUser = new ArrayList<>(createListToMakeRoom(userInQueue, idUser));
            return roomService.CreateNewRoom(listUser, typeOfRoom);
        }catch (Exception e){
            log.error("Error when add new user: || error message {}", e.getMessage());
            return new ApiResponse<>(400, false, "Error when add user", null);
        }
    }
    public ApiResponse<Object> leaveQueue(Long idUser) {
        try{
            ListOperations<String, Long> ops = redisTemplate.opsForList();
            List<Long> queue = ops.range(key, 0, -1);
            if (queue != null && queue.contains(idUser)) {
                ops.remove(key,1,idUser);
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
