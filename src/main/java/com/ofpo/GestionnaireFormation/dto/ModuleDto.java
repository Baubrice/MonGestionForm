package com.ofpo.GestionnaireFormation.dto;

import java.util.List;

public class ModuleDto {

    private String libelle;
    private List<SequenceDto> sequence;

    public List<SequenceDto> getSequence() { return sequence; }

    public void setSequence(List<SequenceDto> sequence) { this.sequence = sequence; }

    public ModuleDto() {
                this.libelle = libelle;
    }

    public String getLibelle() { return libelle; }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
