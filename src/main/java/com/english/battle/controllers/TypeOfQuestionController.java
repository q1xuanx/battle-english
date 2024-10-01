package com.english.battle.controllers;


import com.english.battle.dto.request.TypeCreateRequest;
import com.english.battle.dto.response.ApiResponse;
import com.english.battle.models.TypeOfQuestion;
import com.english.battle.services.TypeOfQuestionService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/type-quest")
public class TypeOfQuestionController {
    private final TypeOfQuestionService typeOfQuestionService;
    @GetMapping("/get-type/{idType}")
    public ResponseEntity<Object> GetTypeOfQuestion(@PathVariable("idType") String idType) {
        ApiResponse<Object> response = typeOfQuestionService.GetTypeOfQuestion(idType);
        return ResponseEntity.status(response.getCode()).body(response);
    }
    @GetMapping("/get-all")
    public ResponseEntity<Object> GetAllTypeOfQuestion() {
        ApiResponse<Object> response = typeOfQuestionService.GetAllType();
        return ResponseEntity.status(response.getCode()).body(response);
    }
    @PutMapping("/update-type/{idType}")
    public ResponseEntity<Object> UpdateType(@PathVariable("idType") String idType, @RequestBody TypeCreateRequest updatedType){
        ApiResponse<Object> response = typeOfQuestionService.UpdateTypeQuestion(idType, updatedType.getNameType());
        return ResponseEntity.status(response.getCode()).body(response);
    }
    @PostMapping("/create-type")
    public ResponseEntity<Object> CreateType(@RequestBody TypeCreateRequest nameType){
        ApiResponse<Object> response = typeOfQuestionService.CreateNewTypeQuestion(nameType.getNameType());
        return ResponseEntity.status(response.getCode()).body(response);
    }
}
