package com.ofpo.GestionnaireFormation.service;

import com.ofpo.GestionnaireFormation.repository.ModuleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ModuleService {

    private final ModuleRepository moduleRepository;

    public ModuleService(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    public List<Module> findAll() {
        return moduleRepository.findAll();
    }

    public Module findById(Long id) {
        return moduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Module non trouvé avec l'id : " + id));
    }

    @Transactional
    public Module createModule(Module module) {
        return moduleRepository.save(module);
    }

    public void deleteModule(Long id) {
        moduleRepository.deleteById(id);
    }

    public void delete(Long id) {
        moduleRepository.deleteById(id);
    }

    public Module update(Long id, Module module) {
        moduleRepository.save(module);
        return module;
    }
}
