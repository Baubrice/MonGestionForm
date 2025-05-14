package com.ofpo.GestionnaireFormation.service;

import com.ofpo.GestionnaireFormation.model.Utilisateur;
import com.ofpo.GestionnaireFormation.repository.UtilisateurRepository;

import java.util.List;

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


    public void create(Utilisateur utilisateur) {
        this.utilisateurRepository.save(utilisateur);
    }
        public void update(Utilisateur utilisateur) {
        this.utilisateurRepository.save(utilisateur);
    }
    public void delete(Utilisateur utilisateur) {

    }

}
