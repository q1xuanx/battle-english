package com.english.battle.controllers;

import com.english.battle.dto.response.ApiResponse;
import com.english.battle.models.Questions;
import com.english.battle.models.User;
import com.english.battle.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/room")
public class RoomController {
    private final RoomService roomService;
    @PostMapping("/create-room/{nameType}")
    public ResponseEntity<Object> CreateRoom( @PathVariable String nameType, @RequestBody List<User> list){
        ApiResponse<Object> response = roomService.CreateNewRoom(list,nameType);
        return ResponseEntity.status(response.getCode()).body(response);
    }
    @PostMapping("/submit/{idRoom}/{idUser}")
    public ResponseEntity<Object> CreateRoom(@PathVariable Long idUser, @PathVariable String idRoom , @RequestBody List<Questions> list){
        ApiResponse<Object> response = roomService.CalculatePoint(list,idUser,idRoom);
        return ResponseEntity.status(response.getCode()).body(response);
    }
}
