package com.ofpo.GestionnaireFormation.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "formation")
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;
    private String numeroOffre;
    private String libelle;
    
    @Column(name = "date_debut")
    private Date dateDebut;
    
    @Column(name = "date_fin")
    private Date dateFin;
    
    @Column(name = "date_debut_pe")
    private Date dateDebutPe;
    
    @Column(name = "date_fin_pe")
    private Date dateFinPe;
    
    @Column(name = "statut")
    private Boolean statut;

    @ManyToMany
    @JoinTable(name = "formation_module",
            joinColumns = @JoinColumn(name = "id_formation"),
            inverseJoinColumns = @JoinColumn(name = "id_module"))
    private Set<Module> modules = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "formations")
    private Set<Utilisateur> utilisateurs = new LinkedHashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumeroOffre() {
        return numeroOffre;
    }

    public void setNumeroOffre(String numeroOffre) {
        this.numeroOffre = numeroOffre;
    }

    public Set<Module> getModules() {
        return modules;
    }

    public void setModules(Set<Module> modules) {
        this.modules = modules;
    }

    public Boolean getStatut() {
        return statut;
    }

    public void setStatut(Boolean statut) {
        this.statut = statut;
    }

    public Date getDateFinPe() {
        return dateFinPe;
    }

    public void setDateFinPe(Date dateFinPe) {
        this.dateFinPe = dateFinPe;
    }

    public Date getDateDebutPe() {
        return dateDebutPe;
    }

    public void setDateDebutPe(Date dateDebutPe) {
        this.dateDebutPe = dateDebutPe;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Set<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(Set<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<Utilisateur> getUtilisateur() {
        return utilisateurs;
    }
}
