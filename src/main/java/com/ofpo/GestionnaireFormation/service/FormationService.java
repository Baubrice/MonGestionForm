package com.ofpo.GestionnaireFormation.service;

import com.ofpo.GestionnaireFormation.entities.Formation;
import com.ofpo.GestionnaireFormation.repository.FormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class FormationService {
    
    @Autowired
    private FormationRepository formationRepository;
    
    public List<Formation> findAll() {
        return formationRepository.findAll();
    }
    
    public Formation findById(Long id) {
        return formationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Formation non trouvée"));
    }
    
    public Formation findByCode(String code) {
        return formationRepository.findByCode(code);
    }
    
    public Formation save(Formation formation) {
        if (formation.getId() == null && formationRepository.existsByCode(formation.getCode())) {
            throw new RuntimeException("Une formation avec ce code existe déjà");
        }
        return formationRepository.save(formation);
    }
    
    public void delete(Long id) {
        formationRepository.deleteById(id);
    }
    
    public Formation createFormation(Formation formation) {
        return save(formation);
    }
    
    public Formation updateFormation(Long id, Formation formation) {
        Formation existingFormation = findById(id);
        formation.setId(id);
        return save(formation);
    }
    
    public void deleteFormation(Long id) {
        delete(id);
    }
    
    public Formation convertToDto(Formation formation) {
        return formation;
    }
}
