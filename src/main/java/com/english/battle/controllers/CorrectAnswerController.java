package com.english.battle.controllers;


import com.english.battle.dto.response.ApiResponse;
import com.english.battle.models.CorrectAnswer;
import com.english.battle.services.CorrectAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/correct-answer")
public class CorrectAnswerController {
    private final CorrectAnswerService correctAnswerService;
    @PutMapping("/update-answer/{idCorrectAnswer}")
    public ResponseEntity<Object> UpdateAnswer(@PathVariable("idCorrectAnswer") String idCorrect, @RequestBody String updatedAnswer){
        ApiResponse<CorrectAnswer> response = correctAnswerService.UpdateCorrectAnswer(idCorrect, updatedAnswer);
        return ResponseEntity.status(response.getCode()).body(response);
    }
    @GetMapping("/get-answer/{idCorrectAnswer}")
    public ResponseEntity<Object> GetAnswer(@PathVariable("idCorrectAnswer") String idCorrect){
        ApiResponse<CorrectAnswer> response =  correctAnswerService.GetCorrectAnswer(idCorrect);
        return ResponseEntity.status(response.getCode()).body(response);
    }
}
