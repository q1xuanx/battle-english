package com.english.battle.services;

import com.english.battle.dto.request.RoleRequest;
import com.english.battle.dto.request.UserCreateRequest;
import com.english.battle.models.Role;
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

    public Role addRole(RoleRequest request){
        Role role = new Role();
        role.setNameRole(request.getNameRole());

        return roleRepository.save(role);
    }

    public List<Role> getAll(){
        List<Role> roles = roleRepository.findAll();
        return roles;
    }

    public Role updateRole(RoleRequest request, Long idRole){
        Role role = roleRepository.findById(idRole).orElseThrow(() -> new RuntimeException("Role not found"));

        role.setNameRole(request.getNameRole());

        return roleRepository.save(role);

    }

    public void deleteRole(Long idRole){
        roleRepository.deleteById(idRole);
    }


}
