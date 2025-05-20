package com.ofpo.GestionnaireFormation.controller;

import com.ofpo.GestionnaireFormation.model.Sequence;
import com.ofpo.GestionnaireFormation.service.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sequences")
public class SequenceController {

    @Autowired
    private final SequenceService sequenceService;

    public SequenceController(SequenceService sequenceService) {
        this.sequenceService = sequenceService;
    }

    @GetMapping({"", "/"})
    public List<Sequence> getAllSequences() {
        return sequenceService.getAllSequences();
    }

    @GetMapping("/module/{moduleId}")
    public Optional<Sequence> getSequencesByModule(@PathVariable Long moduleId) {
        return sequenceService.getSequencesByModuleId(moduleId);
    }

    @GetMapping("/{id}")
    public Optional<Sequence> getSequenceById(@PathVariable Long id) {
        return sequenceService.getSequenceById(id);
    }

    @PostMapping("/create")
    public Sequence createSequence(@RequestBody Sequence sequence) {
        return sequenceService.createSequence(String.valueOf(sequence));
    }

    @PutMapping("/update/{id}")
    public Optional<Sequence> updateSequence(@PathVariable Long id, @RequestBody Sequence details) {
        return sequenceService.updateSequence(id, details);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSequence(@PathVariable Long id) {
        sequenceService.deleteSequence(id);
    }
}
