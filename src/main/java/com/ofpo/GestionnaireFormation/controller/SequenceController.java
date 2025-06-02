package com.ofpo.GestionnaireFormation.controller;

import com.ofpo.GestionnaireFormation.dto.SequenceDto;
import com.ofpo.GestionnaireFormation.model.Sequence;
import com.ofpo.GestionnaireFormation.repository.SequenceRepository;
import com.ofpo.GestionnaireFormation.service.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sequences")
public class SequenceController {

    @Autowired
    private SequenceService sequenceService;
    private final SequenceRepository sequenceRepository;

    public SequenceController(SequenceRepository sequenceRepository) {
        this.sequenceRepository = sequenceRepository;
    }

    // Route pour la vue
    @GetMapping
    public String listSequences(Model model) {
        model.addAttribute("sequences", sequenceService.findAll());
        return "sequences";
    }

    // Routes API
    @GetMapping("/api")
    @ResponseBody
    public ResponseEntity<List<SequenceDto>> findAll() {
        List<Sequence> sequences = sequenceRepository.findAll();
        List<SequenceDto> sequenceDtos = sequences.stream()
            .map(sequenceService::convertToDto)
            .toList();
        return ResponseEntity.ok(sequenceDtos);
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<SequenceDto> findById(@PathVariable Long id) {
        Sequence sequence = sequenceService.findById(id);
        if (sequence == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sequenceService.convertToDto(sequence));
    }

    @PostMapping("/api")
    @ResponseBody
    public ResponseEntity<SequenceDto> createSequence(@RequestBody Sequence sequence) {
        Sequence savedSequence = sequenceService.createSequence(sequence);
        return ResponseEntity.ok(sequenceService.convertToDto(savedSequence));
    }

    @PutMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<SequenceDto> updateSequence(@PathVariable Long id, @RequestBody Sequence sequence) {
        Sequence updatedSequence = sequenceService.update(id, sequence);
        if (updatedSequence == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sequenceService.convertToDto(updatedSequence));
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteSequence(@PathVariable Long id) {
        sequenceService.deleteSequence(id);
        return ResponseEntity.noContent().build();
    }
}
