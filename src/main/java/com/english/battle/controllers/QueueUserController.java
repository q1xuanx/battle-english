package com.english.battle.controllers;
import com.english.battle.dto.response.ApiResponse;
import com.english.battle.services.QueueUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class QueueUserController {
    private final QueueUserService queueUserService;
    @MessageMapping("/joins")
    @SendTo("/battle-english/joinsRoom")
    public ApiResponse<Object> joinsRoom (@Payload Map<String, Object> infoUser){
        System.out.println(infoUser);
        Long idUser = Long.parseLong(infoUser.get("idUser").toString());
        String typeRoom = infoUser.get("typeRoom").toString();
        return queueUserService.searchUser(idUser, typeRoom);
    }
    @MessageMapping("/leave")
    @SendTo("/battle-english/joinsRoom")
    public ApiResponse<Object> leaveRoom (@Payload Map<String,Object> infoUser){
        Long idUser = Long.parseLong(infoUser.get("idUser").toString());
        return queueUserService.leaveQueue(idUser);
    }
}
