package com.ofpo.GestionnaireFormation.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class FormationDto {
    private Long id;
    private String libelle;
    private String numeroOffre;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private LocalDate dateDebutPe;
    private LocalDate dateFinPe;
    private List<ModuleDto> modules;
    private List<UtilisateurDto> utilisateurs;
}

