package com.ofpo.GestionnaireFormation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;

public class FormationDto {

    @JsonProperty("libelle")
    private String libelle;
    
    @JsonProperty("numeroOffre")
    private String numeroOffre;
    
    @JsonProperty("dateDebut")
    private Date dateDebut;
    
    @JsonProperty("dateFin")
    private Date dateFin;
    
    @JsonProperty("dateDebutPe")
    private Date dateDebutPe;
    
    @JsonProperty("dateFinPe")
    private Date dateFinPe;
    
    @JsonProperty("statut")
    private Boolean statut;
    
    @JsonProperty("utilisateurs")
    private List<UtilisateurDto> utilisateurs;
    
    @JsonProperty("modules")
    private List<ModuleDto> modules;

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

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Date getDateDebutPe() {
        return dateDebutPe;
    }

    public void setDateDebutPe(Date dateDebutPe) {
        this.dateDebutPe = dateDebutPe;
    }

    public Date getDateFinPe() {
        return dateFinPe;
    }

    public void setDateFinPe(Date dateFinPe) {
        this.dateFinPe = dateFinPe;
    }

    public Boolean getStatut() {
        return statut;
    }

    public void setStatut(Boolean statut) {
        this.statut = statut;
    }

    public List<UtilisateurDto> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(List<UtilisateurDto> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public List<ModuleDto> getModules() {
        return modules;
    }

    public void setModules(List<ModuleDto> modules) {
        this.modules = modules;
    }
}

