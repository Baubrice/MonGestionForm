package com.ofpo.GestionnaireFormation.service;

import com.ofpo.GestionnaireFormation.entities.Stagiaire;
import com.ofpo.GestionnaireFormation.repository.StagiaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StagiaireService {
    
    @Autowired
    private StagiaireRepository stagiaireRepository;
    
    public List<Stagiaire> getAllStagiaires() {
        return stagiaireRepository.findAll();
    }
    
    public Optional<Stagiaire> getStagiaireById(Long id) {
        return stagiaireRepository.findById(id);
    }
    
    public Stagiaire saveStagiaire(Stagiaire stagiaire) {
        return stagiaireRepository.save(stagiaire);
    }
    
    public void deleteStagiaire(Long id) {
        stagiaireRepository.deleteById(id);
    }
    
    public Stagiaire findByEmail(String email) {
        return stagiaireRepository.findByEmail(email);
    }
    
    public Stagiaire createStagiaire(Stagiaire stagiaire) {
        return saveStagiaire(stagiaire);
    }
} 