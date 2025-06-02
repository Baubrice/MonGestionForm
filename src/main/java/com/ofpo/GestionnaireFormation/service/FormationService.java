package com.ofpo.GestionnaireFormation.service;

import com.ofpo.GestionnaireFormation.dto.FormationDto;
import com.ofpo.GestionnaireFormation.dto.UtilisateurDto;
import com.ofpo.GestionnaireFormation.model.Formation;
import com.ofpo.GestionnaireFormation.repository.FormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FormationService {

    @Autowired
    private FormationRepository formationRepository;

    public List<Formation> findAll() {
        return formationRepository.findAll();
    }

    public Optional<Formation> findById(Long id) {
        return formationRepository.findById(id);
    }

    public Formation save(Formation formation) {
        return formationRepository.save(formation);
    }

    public void deleteById(Long id) {
        formationRepository.deleteById(id);
    }

    public FormationDto convertToDto(Formation formation) {
        if (formation == null) {
            return null;
        }
        
        FormationDto dto = new FormationDto();
        dto.setId(formation.getId());
        dto.setLibelle(formation.getLibelle());
        dto.setNumeroOffre(formation.getNumeroOffre());
        dto.setDateDebut(formation.getDateDebut().toLocalDate());
        dto.setDateFin(formation.getDateFin().toLocalDate());
        dto.setDateDebutPe(formation.getDateDebutPe().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        dto.setDateFinPe(formation.getDateFinPe().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        if (formation.getUtilisateurs() != null) {
            List<UtilisateurDto> utilisateurDtos = formation.getUtilisateurs().stream()
                .map(utilisateur -> {
                    UtilisateurDto userDto = new UtilisateurDto();
                    userDto.setId(utilisateur.getId());
                    userDto.setEmail(utilisateur.getEmail());
                    userDto.setNom(utilisateur.getNom());
                    userDto.setPrenom(utilisateur.getPrenom());
                    return userDto;
                })
                .collect(Collectors.toList());
            dto.setUtilisateurs(utilisateurDtos);
        }

        return dto;
    }

    public FormationRepository getFormationRepository() {
        return formationRepository;
    }
}
