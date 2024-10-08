package com.english.battle.controllers;
import com.english.battle.dto.response.ApiResponse;
import com.english.battle.services.QueueUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class QueueUserController {
    private final QueueUserService queueUserService;
    @MessageMapping("/joins")
    @SendTo("/battle-english/joinsRoom")
    public ApiResponse<Object> joinsRoom (Long idUser, String typeRoom){
        return queueUserService.searchUser(idUser, typeRoom);
    }
}
