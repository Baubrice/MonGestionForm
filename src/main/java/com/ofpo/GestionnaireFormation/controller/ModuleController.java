package com.ofpo.GestionnaireFormation.controller;

import com.ofpo.GestionnaireFormation.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modules")
public class ModuleController {

    @Autowired
    private final ModuleService moduleService;

    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @GetMapping("/")
    public List<Module> findAll() {
        return moduleService.findAll();
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
