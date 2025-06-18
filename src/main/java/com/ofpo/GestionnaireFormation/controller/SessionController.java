package com.ofpo.GestionnaireFormation.controller;

import com.ofpo.GestionnaireFormation.entities.Session;
import com.ofpo.GestionnaireFormation.entities.Stagiaire;
import com.ofpo.GestionnaireFormation.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @GetMapping
    public List<Session> getAllSessions() {
        return sessionService.getAllSessions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Session> getSessionById(@PathVariable Long id) {
        return sessionService.getSessionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/formation/{formationId}")
    public ResponseEntity<List<Session>> findByFormationId(@PathVariable Long formationId) {
        return ResponseEntity.ok(sessionService.findByFormationId(formationId));
    }

    @GetMapping("/periode")
    public ResponseEntity<List<Session>> findByPeriode(
            @RequestParam LocalDate debut,
            @RequestParam LocalDate fin) {
        return ResponseEntity.ok(sessionService.findByDateDebutBetween(debut, fin));
    }

    @GetMapping("/stagiaire/{stagiaireId}")
    public ResponseEntity<List<Session>> findByStagiaireId(@PathVariable Long stagiaireId) {
        return ResponseEntity.ok(sessionService.findByStagiaireId(stagiaireId));
    }

    @PostMapping
    public Session createSession(@RequestBody Session session) {
        return sessionService.saveSession(session);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Session> updateSession(@PathVariable Long id, @RequestBody Session session) {
        return sessionService.getSessionById(id)
                .map(existingSession -> {
                    session.setId(id);
                    return ResponseEntity.ok(sessionService.saveSession(session));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Long id) {
        return sessionService.getSessionById(id)
                .map(session -> {
                    sessionService.deleteSession(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{sessionId}/stagiaires/{stagiaireId}")
    public ResponseEntity<Void> addStagiaireToSession(@PathVariable Long sessionId, @PathVariable Long stagiaireId) {
        sessionService.addStagiaireToSession(sessionId, stagiaireId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{sessionId}/stagiaires/{stagiaireId}")
    public ResponseEntity<Void> removeStagiaireFromSession(@PathVariable Long sessionId, @PathVariable Long stagiaireId) {
        sessionService.removeStagiaireFromSession(sessionId, stagiaireId);
        return ResponseEntity.ok().build();
    }
} 