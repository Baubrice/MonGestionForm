package com.ofpo.GestionnaireFormation.controller;

import com.ofpo.GestionnaireFormation.dto.InscriptionDTO;
import com.ofpo.GestionnaireFormation.model.Utilisateur;
import com.ofpo.GestionnaireFormation.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/inscription")
    public ResponseEntity<?> inscription(@RequestBody InscriptionDTO inscriptionDTO) {
        try {
            Utilisateur utilisateur = authService.inscrire(inscriptionDTO);
            return ResponseEntity.ok(utilisateur);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
} 