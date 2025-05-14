package com.ofpo.GestionnaireFormation.controller;


import com.ofpo.GestionnaireFormation.model.Sequence;
import com.ofpo.GestionnaireFormation.repository.SequenceRepository;
import com.ofpo.GestionnaireFormation.service.SequenceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sequence")
public class SequenceController {

    private final SequenceRepository sequenceRepository;
    private final SequenceService sequenceService;

    public SequenceController(SequenceRepository sequenceRepository, SequenceService sequenceService) {
        this.sequenceRepository = sequenceRepository;
        this.sequenceService = sequenceService;

    @GetMapping("/")
    public List<Sequence> findAll(){ return this.sequenceRepository.findAll();}

    @GetMapping("/{libelle}")
    public Sequence findByLibelle(@PathVariable String libelle) {
        return this.sequenceRepository.findByLibelle(libelle);
        }



    }

}
