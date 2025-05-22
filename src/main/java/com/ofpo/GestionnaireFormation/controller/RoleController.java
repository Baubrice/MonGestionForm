package com.ofpo.GestionnaireFormation.controller;

import com.ofpo.GestionnaireFormation.dto.RoleDto;
import com.ofpo.GestionnaireFormation.model.Role;
import com.ofpo.GestionnaireFormation.service.RoleService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {


    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/")
    public List<RoleDto> findAll() {
        List<Role> roles = this.roleService.findAll();

        return roles.stream().map(role -> new RoleDto(role.getLibelle())).toList();

//        return roleService.findAll();
    }

    @GetMapping("/{id}")
    public Role findById(@PathVariable Long id) {
        return roleService.findById(id);
    }

    @PostMapping("/create")
    public Role create(@RequestBody Role role) {
        return roleService.createRole(role);
    }

    @PutMapping("/update/{id}")
    public Role update(@PathVariable Long id, @RequestBody Role role) {
        return roleService.updateRole(id, role);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        roleService.deleteRole(id);
    }


}
