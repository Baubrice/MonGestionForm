package com.ofpo.GestionnaireFormation.service;

import com.ofpo.GestionnaireFormation.dto.ModuleDto;
import com.ofpo.GestionnaireFormation.dto.SequenceDto;
import com.ofpo.GestionnaireFormation.model.Module;
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
                .orElse(null);
    }

    @Transactional
    public Module createModule(Module module) {
        return moduleRepository.save(module);
    }

    public void deleteModule(Long id) {
        moduleRepository.deleteById(id);
    }

    public Module update(Long id, Module module) {
        Module existingModule = findById(id);
        if (existingModule == null) {
            return null;
        }
        module.setId(id);
        return moduleRepository.save(module);
    }

    public ModuleDto convertToDto(Module module) {
        if (module == null) {
            return null;
        }
        
        ModuleDto dto = new ModuleDto();
        dto.setLibelle(module.getLibelle());

        List<SequenceDto> sequenceDtos = module.getSequences().stream()
            .map(sequence -> new SequenceDto(sequence.getLibelle()))
            .toList();
        dto.setSequence(sequenceDtos);
        
        return dto;
    }
}
