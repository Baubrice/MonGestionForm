package com.ofpo.GestionnaireFormation.service;

import com.ofpo.GestionnaireFormation.dto.ModuleDto;
import com.ofpo.GestionnaireFormation.dto.SequenceDto;
import com.ofpo.GestionnaireFormation.model.Module;
import com.ofpo.GestionnaireFormation.repository.ModuleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

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
        dto.setId(module.getId());
        dto.setNom(module.getLibelle());
        dto.setDescription(module.getDescription());

        List<SequenceDto> sequenceDtos = module.getSequences().stream()
            .map(sequence -> {
                SequenceDto sequenceDto = new SequenceDto();
                sequenceDto.setId(sequence.getId());
                sequenceDto.setLibelle(sequence.getLibelle());
                sequenceDto.setDescription(sequence.getDescription());
                sequenceDto.setDuree(sequence.getDuree());
                return sequenceDto;
            })
            .toList();
        dto.setSequences(sequenceDtos);
        
        return dto;
    }

    public Module save(Module module) {
        return moduleRepository.save(module);
    }

    public void deleteById(Long id) {
        moduleRepository.deleteById(id);
    }
}
