package com.ofpo.GestionnaireFormation.service;

import com.ofpo.GestionnaireFormation.dto.SequenceDto;
import com.ofpo.GestionnaireFormation.model.Sequence;
import com.ofpo.GestionnaireFormation.repository.SequenceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SequenceService {

    private final SequenceRepository sequenceRepository;

    @Autowired
    public SequenceService(SequenceRepository sequenceRepository) {
        this.sequenceRepository = sequenceRepository;
    }

    public Sequence findByLibelle(String libelle) {
        return sequenceRepository.findByLibelle(libelle);
    }

    @Transactional
    public Sequence createSequence(Sequence sequence) {
        return sequenceRepository.save(sequence);
    }

    public void updateSequence(String libelle, Sequence sequence) {
        Sequence existingSequence = sequenceRepository.findByLibelle(libelle);
        if (existingSequence != null) {
            sequence.setId(existingSequence.getId());
            sequenceRepository.save(sequence);
        }
    }

    public void delete(Long id) {
        sequenceRepository.deleteById(id);
    }

    public List<Sequence> getAllSequences() {
        return sequenceRepository.findAll();
    }

    public Optional<Sequence> getSequencesByModuleId(Long moduleId) {
        return sequenceRepository.findByModuleId(moduleId);
    }

    public Optional<Sequence> getSequenceById(Long id) {
        return sequenceRepository.findById(id);
    }

    public void deleteSequence(Long id) {
        sequenceRepository.deleteById(id);
    }

    public Optional<Sequence> updateSequence(Long id, Sequence details) {
        return sequenceRepository.findById(id)
            .map(existingSequence -> {
                details.setId(id);
                return sequenceRepository.save(details);
            });
    }

    public List<Sequence> findAll() {
        return sequenceRepository.findAll();
    }

    public Sequence findById(Long id) {
        return sequenceRepository.findById(id)
                .orElse(null);
    }

    public Sequence update(Long id, Sequence sequence) {
        Sequence existingSequence = findById(id);
        if (existingSequence == null) {
            return null;
        }
        sequence.setId(id);
        return sequenceRepository.save(sequence);
    }

    public SequenceDto convertToDto(Sequence sequence) {
        SequenceDto dto = new SequenceDto();
        dto.setId(sequence.getId());
        dto.setLibelle(sequence.getLibelle());
        dto.setDescription(sequence.getDescription());
        dto.setDuree(sequence.getDuree());
        return dto;
    }
}
