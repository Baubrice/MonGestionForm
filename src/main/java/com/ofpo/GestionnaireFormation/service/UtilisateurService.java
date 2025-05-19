package com.ofpo.GestionnaireFormation.service;

import com.ofpo.GestionnaireFormation.model.Utilisateur;
import com.ofpo.GestionnaireFormation.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public List<Utilisateur> findAll(){
        return this.utilisateurRepository.findAll();
    }

    public Utilisateur findByMatricule(String matricule) {
        return this.utilisateurRepository.findByMatricule(matricule);
    }

    public void createUtilisateur(Utilisateur utilisateur) {
        this.utilisateurRepository.save(utilisateur);
    }
    public void updateUtilisateur(Utilisateur utilisateur) {
        this.utilisateurRepository.save(utilisateur);
    }
    public void deleteUtilisateur(Long utilisateur) { this.utilisateurRepository.delete(utilisateur); }
    public void updateStatut(boolean statut) { this.utilisateurRepository.update(null); }
}
