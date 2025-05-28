package com.ofpo.GestionnaireFormation.service;

import com.ofpo.GestionnaireFormation.model.Utilisateur;
import com.ofpo.GestionnaireFormation.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public List<Utilisateur> findAll() {
        return this.utilisateurRepository.findAll();
    }

    public Utilisateur findByMatricule(String matricule) {
        return this.utilisateurRepository.findByMatricule(matricule);
    }

    @Transactional
    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
        return this.utilisateurRepository.save(utilisateur);
    }

    @Transactional
    public Utilisateur updateUtilisateur(String matricule, Utilisateur utilisateur) {
        Utilisateur existingUtilisateur = findByMatricule(matricule);
        if (existingUtilisateur == null) {
            return null;
        }
        utilisateur.setMatricule(matricule);
        return this.utilisateurRepository.save(utilisateur);
    }

    @Transactional
    public void deleteUtilisateur(String matricule) {
        Utilisateur utilisateur = findByMatricule(matricule);
        if (utilisateur != null) {
            this.utilisateurRepository.delete(utilisateur);
        }
    }

    @Transactional
    public void updateStatut(String matricule, boolean statut) {
        Utilisateur utilisateur = findByMatricule(matricule);
        if (utilisateur != null) {
            utilisateur.setStatut(statut);
            this.utilisateurRepository.save(utilisateur);
        }
    }

    public UtilisateurRepository getUtilisateurRepository() {
        return utilisateurRepository;
    }
}
