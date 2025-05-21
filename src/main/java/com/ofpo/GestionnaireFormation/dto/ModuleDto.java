package com.ofpo.GestionnaireFormation.dto;

public class ModuleDto {

    private String libelle;

    public ModuleDto(String libelle) {
                this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
