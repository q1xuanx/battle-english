package com.english.battle.services;

import com.english.battle.compare.UserComparator;
import com.english.battle.dto.request.UserCreateRequest;
import com.english.battle.dto.response.ApiResponse;
import com.english.battle.models.Roles;
import com.english.battle.models.User;
import com.english.battle.repository.RoleRepository;
import com.english.battle.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public User addUser(UserCreateRequest request){

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword((request.getPassword()));
        user.setAddress(request.getAddress());
        user.setHide(request.isHide());
        user.setGender(request.getGender());
        user.setPhone(request.getPhone());
        user.setStatus(request.getStatus());
        user.setDayOfBirth(request.getDayOfBirth());
        user.setFullName(request.getFullName());

        Optional<Roles> role = roleRepository.findById(Long.valueOf(1));
        user.setRole(role.get());

        return userRepository.save(user);
    }
    public ApiResponse<Object> getLeaderBoard(){
        List<User> getUser = userRepository.findAll();
        getUser.sort(new UserComparator());
        return new ApiResponse<>(200,true,"get list success", getUser);
    }
    public List<User> getAllUsers(){
        List<User> users = userRepository.findAll();
        return users;
    }

    public User getUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUser(UserCreateRequest request, Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(request.getUsername());
        user.setPassword((request.getPassword()));
        user.setAddress(request.getAddress());
        user.setHide(request.isHide());
        user.setGender(request.getGender());
        user.setPhone(request.getPhone());
        user.setStatus(request.getStatus());
        user.setDayOfBirth(request.getDayOfBirth());
        user.setFullName(request.getFullName());
        Optional<Roles> role = roleRepository.findById(Long.valueOf(4));
        user.setRole(role.get());
        return userRepository.save(user);
    }
}
