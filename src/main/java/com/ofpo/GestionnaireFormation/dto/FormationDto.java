package com.ofpo.GestionnaireFormation.dto;

import java.time.Instant;

public class FormationDto {

    private String libelle;
    private String numeroOffre;
    private Instant dateCreation;
    private Instant dateModification;
    private Instant dateRetour;
    private Instant dateRetourModif;


    public FormationDto(String libelle) {

        this.libelle = libelle;
        this.dateCreation = Instant.now();
        this.dateModification = Instant.now();
        this.dateRetour = Instant.now();
        this.dateRetourModif = Instant.now();

    }

        // constructeur vide à utiliser pour debug par exemple
    public FormationDto() {

    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getNumeroOffre() {
        return numeroOffre;
    }

    public void setNumeroOffre(String numeroOffre) {
        this.numeroOffre = numeroOffre;
    }

    public Instant getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Instant dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Instant getDateModification() {
        return dateModification;
    }

    public void setDateModification(Instant dateModification) {
        this.dateModification = dateModification;
    }

    public Instant getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(Instant dateRetour) {
        this.dateRetour = dateRetour;
    }

    public Instant getDateRetourModif() {
        return dateRetourModif;
    }

    public void setDateRetourModif(Instant dateRetourModif) {
        this.dateRetourModif = dateRetourModif;
    }
}
