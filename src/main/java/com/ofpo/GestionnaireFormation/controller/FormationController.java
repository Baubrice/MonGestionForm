package com.ofpo.GestionnaireFormation.controller;

import com.ofpo.GestionnaireFormation.dto.FormationDto;
import com.ofpo.GestionnaireFormation.model.Formation;
import com.ofpo.GestionnaireFormation.repository.FormationRepository;
import com.ofpo.GestionnaireFormation.service.FormationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/formations")
public class FormationController {

    private final FormationService formationService;
    private final FormationRepository formationRepository;

    
    public FormationController(FormationService formationService, FormationRepository formationRepository) {
        this.formationService = formationService;
        this.formationRepository = formationRepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<FormationDto>> findAll() {
        List<Formation> formations = formationRepository.findAll();
        List<FormationDto> formationDtos = formations.stream()
            .map(formationService::convertToDto)
            .collect(Collectors.toList());
        return ResponseEntity.ok(formationDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormationDto> findById(@PathVariable Long id) {
        Formation formation = formationService.findById(id);
        if (formation == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(formationService.convertToDto(formation));
    }

    @PostMapping("/create")
    public ResponseEntity<FormationDto> createFormation(@RequestBody Formation formation) {
        Formation savedFormation = formationService.createFormation(formation);
        return ResponseEntity.ok(formationService.convertToDto(savedFormation));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FormationDto> updateFormation(@PathVariable Long id, @RequestBody Formation formation) {
        Formation updatedFormation = formationService.updateFormation(id, formation);
        if (updatedFormation == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(formationService.convertToDto(updatedFormation));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFormation(@PathVariable Long id) {
        formationService.deleteFormation(id);
        return ResponseEntity.noContent().build();
    }
}
