package com.ofpo.GestionnaireFormation.dto;

import java.util.List;

import lombok.Data;

@Data
public class ModuleDto {
    private Long id;
    private String nom;
    private String description;
    private List<SequenceDto> sequences;
}
