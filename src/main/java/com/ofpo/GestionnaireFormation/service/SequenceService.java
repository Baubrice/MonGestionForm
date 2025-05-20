package com.ofpo.GestionnaireFormation.service;

import com.ofpo.GestionnaireFormation.model.Sequence;
import com.ofpo.GestionnaireFormation.repository.SequenceRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class SequenceService {

    private final SequenceRepository sequenceRepository;
    public SequenceService(SequenceRepository sequenceRepository) {
        this.sequenceRepository = sequenceRepository;
    }
    public Sequence findByLibelle() {
        String libelle = "libelle";
        return this.sequenceRepository.findByLibelle(libelle);
    }

    public Sequence createSequence(@PathVariable String libelle) {
        return new Sequence();
    }

    public void updateSequence(@PathVariable String libelle, Sequence sequence) {

    }

    public void delete(Long id) {
        this.sequenceRepository.deleteById(id);
    }

    public List<Sequence> getAllSequences() {
        return sequenceRepository.findAll();
    }

    public Optional<Sequence> getSequencesByModuleId(Long moduleId) {
        Long id = 0L;
        return sequenceRepository.findById(id);
    }

    public Optional<Sequence> getSequenceById(Long id) {
        return sequenceRepository.findById(id);
    }

    public void deleteSequence(Long id) {
        sequenceRepository.deleteById(id);
    }

    public Optional<Sequence> updateSequence(Long id, Sequence details) {
        sequenceRepository.save(details);
        return sequenceRepository.findById(id);
    }
}
