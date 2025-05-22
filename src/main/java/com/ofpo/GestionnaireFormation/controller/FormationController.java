package com.ofpo.GestionnaireFormation.controller;

import com.ofpo.GestionnaireFormation.dto.FormationDto;
import com.ofpo.GestionnaireFormation.dto.UtilisateurDto;
import com.ofpo.GestionnaireFormation.model.Formation;
import com.ofpo.GestionnaireFormation.service.FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/formations")
public class FormationController {

    @Autowired
    private final FormationService formationService;
    private final FormationController formationRepository;

    public FormationController(FormationService formationService, FormationController formationRepository) {
        this.formationService = formationService;
        this.formationRepository = formationRepository;
    }

    @GetMapping("/")
    public List<Formation> findAll() {
//        return formationService.findAll();
        List<Formation> formations = this.formationRepository.findAll();

        formations.stream().map(formation -> new FormationDto(formation.getLibelle(), formation.getNumeroOffre(), formation.getDateCreation(), formation.getDateModification(), formation.getDateRetour(), formation.getDateRetourModif()){
            FormationDto dto = new FormationDto();


//            dto.setLibelle(formation.getLibelle());
//            dto.setNumeroOffre(formation.getNumeroOffre());
//            dto.setDateCreation(formation.getDateCreation());
//            dto.setDateModification(formation.getDateModification());
//            dto.setDateRetour(formation.getDateRetour());
//            dto.setDateRetourModif(formation.getDateRetourModif());

//            List<UtilisateurDto> utilisateurDtos = formation.getUtilisateur().stream()
//                    .map(utilisateur -> new UtilisateurDto(utilisateur.getMatricule()))
//                    .toList();
//            dto.setFormation(formationDtos);
            return dto;
        }).toList();}

    @GetMapping("/{id}")
    public Formation findById(@PathVariable Long id) {
        return formationService.findById(id);
    }

    @PostMapping("/create")
    public Formation createFormation(@RequestBody Formation formation) {
        return formationService.createFormation(formation);
    }

    @PutMapping("/update/{id}")
    public Formation updateFormation(@PathVariable Long id, @RequestBody Formation formation) {
        return formationService.updateFormation(id, formation);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFormation(@PathVariable Long id) {
        formationService.deleteFormation(id);
    }

}
