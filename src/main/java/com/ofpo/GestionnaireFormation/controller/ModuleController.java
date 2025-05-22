package com.ofpo.GestionnaireFormation.controller;

import com.ofpo.GestionnaireFormation.dto.ModuleDto;
import com.ofpo.GestionnaireFormation.dto.SequenceDto;
import com.ofpo.GestionnaireFormation.repository.ModuleRepository;
import com.ofpo.GestionnaireFormation.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ofpo.GestionnaireFormation.model.Module;
import com.ofpo.GestionnaireFormation.model.Sequence;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.yaml.snakeyaml.nodes.NodeId.sequence;

@RestController
@RequestMapping("/modules")
public class ModuleController {

    @Autowired
    private final ModuleService moduleService;
    @Autowired
    private ModuleRepository moduleRepository;

    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @GetMapping("/")
    public List<ModuleDto> findAll() {
        List<Module> modules = moduleRepository.findAll();

        return modules.stream().filter(module -> {
            ModuleDto dto = new ModuleDto();

            dto.setLibelle(module.getLibelle());

            List<SequenceDto> SequenceDtos = module.getSequences().stream().map(sequence -> new SequenceDto(sequence.getLibelle())).toList();
            dto.setSequence(SequenceDtos);
            return dto;
        }).toList();

//        return moduleService.findAll();
    }

    @GetMapping("/{id}")
    public Module findById(@PathVariable Long id) {
        return moduleService.findById(id);
    }

    @PostMapping("/create")
    public Module create(@RequestBody Module module) {
        return moduleService.createModule(module);
    }

    @PutMapping("/update/{id}")
    public Module update(@PathVariable Long id, @RequestBody Module module) {
        return moduleService.update(id, module);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteModule(@PathVariable Long id) {
        moduleService.deleteModule(id);
    }
}
