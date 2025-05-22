package com.ofpo.GestionnaireFormation.dto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

public class FormationDto {

    private String libelle;
    private String numeroOffre;
    private LocalDateTime dateCreation;
    private LocalDateTime dateModification;
    private LocalDateTime dateRetour;
    private LocalDateTime dateRetourModif;


    public FormationDto(String libelle,String numeroOffre , Date dateCreation, Date dateModification, Date dateRetour, Date dateRetourModif) {

        this.libelle = libelle;
        this.numeroOffre = numeroOffre;
        this.dateCreation = dateCreation;
        this.dateModification = dateModification;
        this.dateRetour = dateRetour;
        this.dateRetourModif = dateRetourModif;



    }

        // constructeur vide à utiliser pour debug par exemple
    public FormationDto() {

    }

    public FormationDto(String libelle, String numeroOffre, Instant dateCreation, Instant dateModification, Instant dateRetour, Instant dateRetourModif) {
        return;
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

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDateTime getDateModification() {
        return dateModification;
    }

    public void setDateModification(LocalDateTime dateModification) {
        this.dateModification = dateModification;
    }

    public LocalDateTime getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(LocalDateTime dateRetour) {
        this.dateRetour = dateRetour;
    }

    public LocalDateTime getDateRetourModif() {
        return dateRetourModif;
    }

    public void setDateRetourModif(LocalDateTime dateRetourModif) {
        this.dateRetourModif = dateRetourModif;
    }

    public Arrays getUtilisateur() {
        return null;
    }
}
