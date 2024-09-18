package com.english.battle.controllers;

import com.english.battle.dto.request.RoleRequest;
import com.english.battle.dto.request.UserCreateRequest;
import com.english.battle.dto.response.ApiResponse;
import com.english.battle.models.Roles;
import com.english.battle.services.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping
    ApiResponse<Roles> addUser(@RequestBody @Valid RoleRequest request) {
        ApiResponse<Roles> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setData(roleService.addRole(request));
        return response;
    }

    @GetMapping
    ApiResponse<List<Roles>> getAll(){
        ApiResponse<List<Roles>> response = new ApiResponse<>();
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
    ApiResponse<Roles> updateRole(@PathVariable("idRole") Long idRole, @RequestBody @Valid RoleRequest request) {
        ApiResponse response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Successfully updated user");
        response.setData(roleService.updateRole(request,idRole));
        return response;
    }
}
