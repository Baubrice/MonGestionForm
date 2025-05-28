package com.ofpo.GestionnaireFormation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ModuleDto {

    @JsonProperty("libelle")
    private String libelle;
    
    @JsonProperty("sequences")
    private List<SequenceDto> sequences;

    public ModuleDto() {
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<SequenceDto> getSequences() {
        return sequences;
    }

    public void setSequences(List<SequenceDto> sequences) {
        this.sequences = sequences;
    }

    public void setSequence(List<SequenceDto> sequenceDtos) {
        this.sequences = sequenceDtos;
    }
}
