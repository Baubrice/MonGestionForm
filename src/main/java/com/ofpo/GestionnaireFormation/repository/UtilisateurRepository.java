package com.ofpo.GestionnaireFormation.repository;

import com.ofpo.GestionnaireFormation.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{
    Utilisateur findByMatricule(String matricule);
}
