package com.ofpo.GestionnaireFormation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Auth0TestController {

    @GetMapping("/public")
    public ResponseEntity<String> publicEndpoint() {
        return ResponseEntity.ok("Ceci est un endpoint public");
    }

    @GetMapping("/private")
    public ResponseEntity<String> privateEndpoint(@AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok("Ceci est un endpoint privé. Authentifié en tant que : " + jwt.getSubject());
    }

    @GetMapping("/private-scoped")
    public ResponseEntity<String> privateScopedEndpoint(@AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok("Ceci est un endpoint privé avec scope. Authentifié en tant que : " + jwt.getSubject());
    }
} 