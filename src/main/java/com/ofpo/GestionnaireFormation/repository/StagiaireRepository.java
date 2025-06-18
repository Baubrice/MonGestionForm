package com.ofpo.GestionnaireFormation.repository;

import com.ofpo.GestionnaireFormation.entities.Stagiaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StagiaireRepository extends JpaRepository<Stagiaire, Long> {
    Stagiaire findByEmail(String email);
    boolean existsByEmail(String email);
} 