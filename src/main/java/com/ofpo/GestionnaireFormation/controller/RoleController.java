package com.ofpo.GestionnaireFormation.controller;

import com.ofpo.GestionnaireFormation.model.Role;
import com.ofpo.GestionnaireFormation.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> listRoles() {
        return ResponseEntity.ok(roleService.findAll());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> createRole(@RequestBody Role role) {
        try {
            Role savedRole = roleService.save(role);
            return ResponseEntity.ok(savedRole);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors de la création du rôle: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> updateRole(@PathVariable Long id, @RequestBody Role role) {
        try {
            role.setId(id);
            Role updatedRole = roleService.save(role);
            return ResponseEntity.ok(updatedRole);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors de la modification du rôle: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteRole(@PathVariable Long id) {
        try {
            roleService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors de la suppression du rôle: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/toggle-status")
    @ResponseBody
    public ResponseEntity<?> toggleStatus(@PathVariable Long id) {
        try {
            Role role = roleService.findById(id)
                .orElseThrow(() -> new RuntimeException("Rôle non trouvé"));
            role.setStatut(!role.isStatut());
            Role updatedRole = roleService.save(role);
            return ResponseEntity.ok(updatedRole);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors du changement de statut: " + e.getMessage());
        }
    }

    @GetMapping("/view")
    public String viewRoles(Model model) {
        model.addAttribute("roles", roleService.findAll());
        return "roles";
    }
}
