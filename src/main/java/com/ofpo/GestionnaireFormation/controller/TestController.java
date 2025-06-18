package com.ofpo.GestionnaireFormation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class TestController {

    @GetMapping("/test")
    public ResponseEntity<?> test(Authentication authentication) {
        return ResponseEntity.ok("Authentifié en tant que : " + authentication.getName() + 
                               " avec les rôles : " + authentication.getAuthorities());
    }
} 