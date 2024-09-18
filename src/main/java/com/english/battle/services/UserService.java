package com.english.battle.services;

import com.english.battle.dto.request.UserCreateRequest;
import com.english.battle.models.Role;
import com.english.battle.models.User;
import com.english.battle.repository.RoleRepository;
import com.english.battle.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

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

        Optional<Role> role = roleRepository.findById(Long.valueOf(4));
        user.setRole(role.get());

        return userRepository.save(user);
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

        Optional<Role> role = roleRepository.findById(Long.valueOf(4));
        user.setRole(role.get());

        return userRepository.save(user);

    }
}
