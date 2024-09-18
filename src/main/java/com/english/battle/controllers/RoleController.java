package com.english.battle.controllers;

import com.english.battle.dto.request.RoleRequest;
import com.english.battle.dto.request.UserCreateRequest;
import com.english.battle.dto.response.ApiResponse;
import com.english.battle.models.Role;
import com.english.battle.models.User;
import com.english.battle.services.RoleService;
import com.english.battle.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping
    ApiResponse<Role> addUser(@RequestBody @Valid RoleRequest request) {
        ApiResponse<Role> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setData(roleService.addRole(request));
        return response;
    }

    @GetMapping
    ApiResponse<List<Role>> getAll(){
        ApiResponse<List<Role>> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setData(roleService.getAll());
        return response;
    }

    @DeleteMapping("/{idRole}")
    ApiResponse<Void> deleteRole(@PathVariable Long idRole){
        ApiResponse<Void> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Delete role successfully");
        roleService.deleteRole(idRole);
        return response;
    }

    @PutMapping("/{idRole}")
    ApiResponse<Role> updateRole(@PathVariable("idRole") Long idRole, @RequestBody @Valid RoleRequest request) {
        ApiResponse response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Successfully updated user");
        response.setData(roleService.updateRole(request,idRole));
        return response;
    }
}
