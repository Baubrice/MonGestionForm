package com.ofpo.GestionnaireFormation.model;

import jakarta.persistence.*;

@Entity
@Table(name = "formation_document")
public class FormationDocument {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_formation")
    private Formation idFormation;

    public Formation getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(Formation idFormation) {
        this.idFormation = idFormation;
    }

}