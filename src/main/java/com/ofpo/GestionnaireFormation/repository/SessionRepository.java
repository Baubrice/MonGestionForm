package com.ofpo.GestionnaireFormation.repository;

import com.ofpo.GestionnaireFormation.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByFormationId(Long formationId);
    List<Session> findByDateDebutBetween(LocalDate debut, LocalDate fin);
    List<Session> findByStagiairesId(Long stagiaireId);
} 