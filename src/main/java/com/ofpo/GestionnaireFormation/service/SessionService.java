package com.ofpo.GestionnaireFormation.service;

import com.ofpo.GestionnaireFormation.entities.Session;
import com.ofpo.GestionnaireFormation.entities.Stagiaire;
import com.ofpo.GestionnaireFormation.repository.SessionRepository;
import com.ofpo.GestionnaireFormation.repository.StagiaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Service
@Transactional
public class SessionService {
    
    @Autowired
    private SessionRepository sessionRepository;
    
    @Autowired
    private StagiaireRepository stagiaireRepository;
    
    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }
    
    public Optional<Session> getSessionById(Long id) {
        return sessionRepository.findById(id);
    }
    
    public List<Session> findByFormationId(Long formationId) {
        return sessionRepository.findByFormationId(formationId);
    }
    
    public List<Session> findByDateDebutBetween(LocalDate debut, LocalDate fin) {
        return sessionRepository.findByDateDebutBetween(debut, fin);
    }
    
    public List<Session> findByStagiaireId(Long stagiaireId) {
        return sessionRepository.findByStagiairesId(stagiaireId);
    }
    
    public Session saveSession(Session session) {
        validateSession(session);
        return sessionRepository.save(session);
    }
    
    public void deleteSession(Long id) {
        sessionRepository.deleteById(id);
    }
    
    public void addStagiaireToSession(Long sessionId, Long stagiaireId) {
        Optional<Session> sessionOpt = sessionRepository.findById(sessionId);
        Optional<Stagiaire> stagiaireOpt = stagiaireRepository.findById(stagiaireId);
        
        if (sessionOpt.isPresent() && stagiaireOpt.isPresent()) {
            Session session = sessionOpt.get();
            Stagiaire stagiaire = stagiaireOpt.get();
            
            if (session.getStagiaires() == null) {
                session.setStagiaires(new ArrayList<>());
            }
            
            session.getStagiaires().add(stagiaire);
            sessionRepository.save(session);
        }
    }
    
    public void removeStagiaireFromSession(Long sessionId, Long stagiaireId) {
        Optional<Session> sessionOpt = sessionRepository.findById(sessionId);
        Optional<Stagiaire> stagiaireOpt = stagiaireRepository.findById(stagiaireId);
        
        if (sessionOpt.isPresent() && stagiaireOpt.isPresent()) {
            Session session = sessionOpt.get();
            Stagiaire stagiaire = stagiaireOpt.get();
            
            if (session.getStagiaires() != null) {
                session.getStagiaires().remove(stagiaire);
                sessionRepository.save(session);
            }
        }
    }
    
    private void validateSession(Session session) {
        if (session.getDateDebut().isAfter(session.getDateFin())) {
            throw new RuntimeException("La date de début doit être antérieure à la date de fin");
        }
        if (session.getNombrePlaces() <= 0) {
            throw new RuntimeException("Le nombre de places doit être supérieur à 0");
        }
    }
} 