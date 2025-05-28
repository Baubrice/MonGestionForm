package com.ofpo.GestionnaireFormation.controller;

import com.ofpo.GestionnaireFormation.dto.ModuleDto;
import com.ofpo.GestionnaireFormation.dto.SequenceDto;
import com.ofpo.GestionnaireFormation.model.Module;
import com.ofpo.GestionnaireFormation.repository.ModuleRepository;
import com.ofpo.GestionnaireFormation.service.ModuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/module")
public class ModuleController {

    private final ModuleService moduleService;
    private final ModuleRepository moduleRepository;

    
    public ModuleController(ModuleService moduleService, ModuleRepository moduleRepository) {
        this.moduleService = moduleService;
        this.moduleRepository = moduleRepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<ModuleDto>> findAll() {
        List<Module> modules = moduleRepository.findAll();
        List<ModuleDto> moduleDtos = modules.stream()
            .map(module -> {
                ModuleDto dto = new ModuleDto();
                dto.setLibelle(module.getLibelle());

                List<SequenceDto> sequenceDtos = module.getSequences().stream()
                    .map(sequence -> new SequenceDto(sequence.getLibelle()))
                    .toList();
                dto.setSequences(sequenceDtos);
                return dto;
            })
            .toList();
        return ResponseEntity.ok(moduleDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModuleDto> findById(@PathVariable Long id) {
        Module module = moduleService.findById(id);
        if (module == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(moduleService.convertToDto(module));
    }

    @PostMapping("/create")
    public ResponseEntity<ModuleDto> create(@RequestBody Module module) {
        Module savedModule = moduleService.createModule(module);
        return ResponseEntity.ok(moduleService.convertToDto(savedModule));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ModuleDto> update(@PathVariable Long id, @RequestBody Module module) {
        Module updatedModule = moduleService.update(id, module);
        if (updatedModule == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(moduleService.convertToDto(updatedModule));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteModule(@PathVariable Long id) {
        moduleService.deleteModule(id);
        return ResponseEntity.noContent().build();
    }
}
