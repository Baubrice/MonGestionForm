package com.ofpo.GestionnaireFormation.dto;

public class RoleDto {

    private String libelle;

    public RoleDto(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
