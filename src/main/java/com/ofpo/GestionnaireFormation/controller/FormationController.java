package com.ofpo.GestionnaireFormation.controller;

import com.ofpo.GestionnaireFormation.model.Formation;
import com.ofpo.GestionnaireFormation.service.FormationService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Data
@RestController
@RequestMapping("/formations")
public class FormationController {

    @Autowired
    private final FormationService formationService;

    public FormationController(FormationService formationService) {
        this.formationService = formationService;
    }

    @GetMapping("/")
    public List<Formation> findAll() {
        return formationService.findAll();
    }

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
