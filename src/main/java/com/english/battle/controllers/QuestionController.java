package com.english.battle.controllers;
import com.english.battle.dto.response.ApiResponse;
import com.english.battle.models.Questions;
import com.english.battle.services.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/questions")
public class QuestionController {
    private final QuestionService questionService;
    @PostMapping("/add-new")
    public ResponseEntity<Object> AddQuestion (@RequestBody List<Questions> questList){
        ApiResponse<Object> listAdd = questionService.CreateQuestion(questList);
        return ResponseEntity.status(listAdd.getCode()).body(listAdd);
    }
    @GetMapping("/make-test")
    public ResponseEntity<Object> MakeTask(@RequestParam ("sizeOfList") int sizeOfList, @RequestParam String nameType){
        ApiResponse<Object> list = questionService.MakeListQuest(sizeOfList, nameType);
        return ResponseEntity.status(list.getCode()).body(list);
    }
}
