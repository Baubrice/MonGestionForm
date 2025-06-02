package com.ofpo.GestionnaireFormation.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "formation")
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nom;
    
    @Column(length = 1000)
    private String description;
    
    @Column(name = "numero_offre")
    private String numeroOffre;
    
    @Column(name = "libelle")
    private String libelle;
    
    @Column(name = "date_debut")
    private LocalDateTime dateDebut;
    
    @Column(name = "date_fin")
    private LocalDateTime dateFin;
    
    @Column(name = "date_debut_pe")
    private Date dateDebutPe;
    
    @Column(name = "date_fin_pe")
    private Date dateFinPe;
    
    private boolean statut = true;

    @ManyToMany
    @JoinTable(
        name = "formation_module",
        joinColumns = @JoinColumn(name = "id_formation"),
        inverseJoinColumns = @JoinColumn(name = "id_module")
    )
    private Set<Module> modules = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "formations")
    private Set<Utilisateur> utilisateurs = new LinkedHashSet<>();

    public Formation() {}

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

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
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

    public boolean isStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    public Set<Module> getModules() {
        return modules;
    }

    public void setModules(Set<Module> modules) {
        this.modules = modules;
    }

    public Set<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(Set<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }
}
