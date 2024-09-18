package com.english.battle.services;

import com.english.battle.dto.request.RoleRequest;
import com.english.battle.dto.request.UserCreateRequest;
import com.english.battle.models.Roles;
import com.english.battle.models.User;
import com.english.battle.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Roles addRole(RoleRequest request){
        Roles role = new Roles();
        role.setNameRole(request.getNameRole());

        return roleRepository.save(role);
    }

    public List<Roles> getAll(){
        List<Roles> roles = roleRepository.findAll();
        return roles;
    }

    public Roles updateRole(RoleRequest request, Long idRole){
        Roles role = roleRepository.findById(idRole).orElseThrow(() -> new RuntimeException("Role not found"));

        role.setNameRole(request.getNameRole());

        return roleRepository.save(role);

    }

    public void deleteRole(Long idRole){
        roleRepository.deleteById(idRole);
    }


}
