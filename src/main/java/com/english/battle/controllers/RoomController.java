package com.english.battle.controllers;

import com.english.battle.dto.request.UserCreateRoomRequest;
import com.english.battle.dto.response.ApiResponse;
import com.english.battle.models.Questions;
import com.english.battle.models.User;
import com.english.battle.services.RoomService;
import lombok.Getter;
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
    public ResponseEntity<Object> CreateRoom(@PathVariable String nameType, @RequestBody List<UserCreateRoomRequest> list){
        ApiResponse<Object> response = roomService.CreateNewRoom(list,nameType);
        return ResponseEntity.status(response.getCode()).body(response);
    }
    @PostMapping("/submit")
    public ResponseEntity<Object> CreateRoom(@RequestParam Long idUser, @RequestParam String idRoom , @RequestBody List<Questions> list){
        ApiResponse<Object> response = roomService.CalculatePoint(list,idUser,idRoom);
        return ResponseEntity.status(response.getCode()).body(response);
    }
    @GetMapping("/get-rank/{idRoom}")
    public ResponseEntity<Object> GetRank(@PathVariable String idRoom){
        ApiResponse<Object> response = roomService.CalculateRank(idRoom);
        return ResponseEntity.status(response.getCode()).body(response);
    }
}
//Create room -> join room -> submit list question
