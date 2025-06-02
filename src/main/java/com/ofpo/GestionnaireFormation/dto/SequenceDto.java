package com.ofpo.GestionnaireFormation.dto;

import lombok.Data;

@Data
public class SequenceDto {
    private Long id;
    private String libelle;
    private String description;
    private Integer duree;
}
