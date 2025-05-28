package com.ofpo.GestionnaireFormation.service;

import com.ofpo.GestionnaireFormation.dto.FormationDto;
import com.ofpo.GestionnaireFormation.dto.UtilisateurDto;
import com.ofpo.GestionnaireFormation.model.Formation;
import com.ofpo.GestionnaireFormation.repository.FormationRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormationService {

    private final FormationRepository formationRepository;

    public FormationService(FormationRepository formationRepository) {
        this.formationRepository = formationRepository;
    }

    public List<Formation> findAll() {
        return formationRepository.findAll();
    }

    public Formation findById(Long id) {
        return formationRepository.findById(id)
                .orElse(null);
    }

    public Formation createFormation(Formation formation) {
        return formationRepository.save(formation);
    }

    public Formation updateFormation(Long id, Formation formation) {
        Formation existingFormation = findById(id);
        if (existingFormation == null) {
            return null;
        }
        formation.setId(id);
        return formationRepository.save(formation);
    }

    public void deleteFormation(Long id) {
        formationRepository.deleteById(id);
    }

    public FormationDto convertToDto(Formation formation) {
        if (formation == null) {
            return null;
        }
        
        FormationDto dto = new FormationDto();
        dto.setLibelle(formation.getLibelle());
        dto.setNumeroOffre(formation.getNumeroOffre());
        dto.setDateDebut(formation.getDateDebut());
        dto.setDateFin(formation.getDateFin());
        dto.setDateDebutPe(formation.getDateDebutPe());
        dto.setDateFinPe(formation.getDateFinPe());

        if (formation.getUtilisateur() != null) {
            List<UtilisateurDto> utilisateurDtos = formation.getUtilisateur().stream()
                .map(utilisateur -> {
                    UtilisateurDto userDto = new UtilisateurDto();
                    userDto.setMatricule(utilisateur.getMatricule());
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
