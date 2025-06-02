package com.ofpo.GestionnaireFormation.service;

import com.ofpo.GestionnaireFormation.model.Utilisateur;
import com.ofpo.GestionnaireFormation.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public List<Utilisateur> findAll() {
        return utilisateurRepository.findAll();
    }

    public Optional<Utilisateur> findByEmail(String email) {
        return this.utilisateurRepository.findByEmail(email);
    }

    public List<Utilisateur> findByNom(String nom) {
        return this.utilisateurRepository.findByNomContainingIgnoreCase(nom);
    }

    @Transactional
    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
        return this.utilisateurRepository.save(utilisateur);
    }

    @Transactional
    public Utilisateur updateUtilisateur(String email, Utilisateur utilisateur) {
        return this.utilisateurRepository.findByEmail(email)
            .map(existingUtilisateur -> {
                utilisateur.setId(existingUtilisateur.getId());
                return this.utilisateurRepository.save(utilisateur);
            })
            .orElse(null);
    }

    @Transactional
    public void deleteUtilisateur(String email) {
        this.utilisateurRepository.findByEmail(email)
            .ifPresent(this.utilisateurRepository::delete);
    }

    public UtilisateurRepository getUtilisateurRepository() {
        return utilisateurRepository;
    }

    public Utilisateur save(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public void deleteById(Long id) {
        utilisateurRepository.deleteById(id);
    }
}
