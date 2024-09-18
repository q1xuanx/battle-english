package com.english.battle.controllers;

import com.english.battle.dto.request.UserCreateRequest;
import com.english.battle.dto.response.ApiResponse;
import com.english.battle.models.User;
import com.english.battle.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    ApiResponse<User> addUser(@RequestBody @Valid UserCreateRequest request) {
        ApiResponse<User> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setData(userService.addUser(request));
        return response;
    }

    @GetMapping
    ApiResponse<List<User>> getAllUsers() {
        ApiResponse<List<User>> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setData(userService.getAllUsers());
        response.setMessage("Get all users successfully");
        return response;
    }

    @GetMapping("/{userId}")
    ApiResponse<User> getUser(@PathVariable("userId") Long userId) {
        ApiResponse<User> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Successfully retrieved user");
        response.setData(userService.getUserById(userId));
        return response;
    }

    @PutMapping("/{userId}")
    ApiResponse<User> updateUser(@PathVariable("userId") Long userId, @RequestBody @Valid UserCreateRequest request) {
        ApiResponse response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Successfully updated user");
        response.setData(userService.updateUser(request,userId));
        return response;
    }
}
