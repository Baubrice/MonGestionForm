package com.ofpo.GestionnaireFormation.service;

import com.ofpo.GestionnaireFormation.model.Sequence;
import com.ofpo.GestionnaireFormation.repository.SequenceRepository;
import org.springframework.web.bind.annotation.PathVariable;

public class SequenceService {

    private final SequenceRepository sequenceRepository;
    public SequenceService(SequenceRepository sequenceRepository) {
        this.sequenceRepository = sequenceRepository;
    }
    public Sequence findByLibelle(@PathVariable String libelle) {
        return this.sequenceRepository.findByLibelle(libelle);
    }

    public void createSequence(@PathVariable String libelle) {
        Sequence sequence = new Sequence();
    }

    public void updateSequence(@PathVariable String libelle, Sequence sequence) {

    }



}
