package com.ofpo.GestionnaireFormation.service;

import com.ofpo.GestionnaireFormation.model.Formation;
import com.ofpo.GestionnaireFormation.repository.FormationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

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
                .orElseThrow(() -> new RuntimeException("Formation non trouvée avec l'id : " + id));
    }

    public Formation createFormation(Formation formation) {
        return formationRepository.save(formation);
    }

    public Formation updateFormation(Long id, Formation formation) {
        return formationRepository.save(formation);
    }

    public void deleteFormation(Long id) {
        formationRepository.deleteById(id);
    }

    //    public Formation create(Formation formation) {
//        return formationRepository.save(formation);
//    }

//    public Formation update(Long id, Formation details) {
//        Formation f = findById(id);
//        f.setLibelle(details.getLibelle());
//        f.setNumeroOffre(details.getNumeroOffre());
//        f.setDateDebut(details.getDateDebut());
//        f.setDateFin(details.getDateFin());
//        f.setDateDebutPe(details.getDateDebutPe());
//        f.setDateFinPe(details.getDateFinPe());
//        f.setStatut(details.getStatut());
//        f.setModules(details.getModules());
//        return formationRepository.save(f);
//    }

}
