package com.ofpo.GestionnaireFormation.controller;

import com.ofpo.GestionnaireFormation.dto.ModuleDto;
import com.ofpo.GestionnaireFormation.model.Module;
import com.ofpo.GestionnaireFormation.repository.ModuleRepository;
import com.ofpo.GestionnaireFormation.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/modules")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;
    private final ModuleRepository moduleRepository;

    public ModuleController(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    // Route pour la vue
    @GetMapping
    public String listModules(Model model) {
        model.addAttribute("modules", moduleService.findAll());
        return "modules";
    }

    // Routes API
    @GetMapping("/api")
    @ResponseBody
    public ResponseEntity<List<ModuleDto>> findAll() {
        List<Module> modules = moduleRepository.findAll();
        List<ModuleDto> moduleDtos = modules.stream()
            .map(moduleService::convertToDto)
            .toList();
        return ResponseEntity.ok(moduleDtos);
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<ModuleDto> findById(@PathVariable Long id) {
        Module module = moduleService.findById(id);
        if (module == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(moduleService.convertToDto(module));
    }

    @PostMapping("/api")
    @ResponseBody
    public ResponseEntity<ModuleDto> createModule(@RequestBody Module module) {
        Module savedModule = moduleService.createModule(module);
        return ResponseEntity.ok(moduleService.convertToDto(savedModule));
    }

    @PutMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<ModuleDto> updateModule(@PathVariable Long id, @RequestBody Module module) {
        Module updatedModule = moduleService.update(id, module);
        if (updatedModule == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(moduleService.convertToDto(updatedModule));
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteModule(@PathVariable Long id) {
        moduleService.deleteModule(id);
        return ResponseEntity.noContent().build();
    }
}
