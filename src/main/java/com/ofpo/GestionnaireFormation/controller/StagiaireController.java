package com.ofpo.GestionnaireFormation.controller;

import com.ofpo.GestionnaireFormation.entities.Stagiaire;
import com.ofpo.GestionnaireFormation.service.StagiaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stagiaires")
public class StagiaireController {
    @Autowired
    private StagiaireService stagiaireService;
    
    @GetMapping
    public List<Stagiaire> getAllStagiaires() {
        return stagiaireService.getAllStagiaires();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Stagiaire> getStagiaireById(@PathVariable Long id) {
        return stagiaireService.getStagiaireById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Stagiaire createStagiaire(@RequestBody Stagiaire stagiaire) {
        return stagiaireService.saveStagiaire(stagiaire);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Stagiaire> updateStagiaire(@PathVariable Long id, @RequestBody Stagiaire stagiaire) {
        return stagiaireService.getStagiaireById(id)
                .map(existingStagiaire -> {
                    stagiaire.setId(id);
                    return ResponseEntity.ok(stagiaireService.saveStagiaire(stagiaire));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStagiaire(@PathVariable Long id) {
        return stagiaireService.getStagiaireById(id)
                .map(stagiaire -> {
                    stagiaireService.deleteStagiaire(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
} 