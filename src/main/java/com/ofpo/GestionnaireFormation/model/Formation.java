package com.ofpo.GestionnaireFormation.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "formation")
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    private String numeroOffre;
    private Instant dateCreation;
    private Instant dateModification;
    private Instant dateRetour;
    private Instant dateRetourModif;
    private Boolean status;

    @Column(name = "date_debut", nullable = false)
    private Instant dateDebut;

    @Column(name = "date_fin", nullable = false)
    private Instant dateFin;

    @Column(name = "date_debut_pe", nullable = false)
    private Instant dateDebutPe;

    @Column(name = "date_fin_pe", nullable = false)
    private Instant dateFinPe;

    @Getter
    @ColumnDefault("0")
    @Column(name = "statut", nullable = false)
    private Boolean statut = false;

    @Getter
    @ManyToMany(mappedBy = "formations")
    private Set<Module> modules = new LinkedHashSet<>();

    @Getter
    @ManyToMany(mappedBy = "formations")
    private Set<Utilisateur> utilisateurs = new LinkedHashSet<>();

    public void setUtilisateurs(Set<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public void setModules(Set<Module> modules) { this.modules = modules; }

    public void setStatut(Boolean statut) {
        this.statut = statut;
    }

    public Instant getDateFinPe() {
        return dateFinPe;
    }
    public void setDateFinPe(Instant dateFinPe) {
        this.dateFinPe = dateFinPe;
    }

    public Instant getDateDebutPe() {
        return dateDebutPe;
    }
    public void setDateDebutPe(Instant dateDebutPe) {
        this.dateDebutPe = dateDebutPe;
    }

    public Instant getDateFin() {
        return dateFin;
    }
    public void setDateFin(Instant dateFin) {
        this.dateFin = dateFin;
    }

    public Instant getDateDebut() {
        return dateDebut;
    }
    public void setDateDebut(Instant dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Object getLibelle() {
        return libelle;
    }
    public void setLibelle(Object libelle) { this.libelle = libelle.toString(); }

    public Object getNumeroOffre() { return numeroOffre == null ? "" : numeroOffre;  }
    public void setNumeroOffre(Object numeroOffre) { this.numeroOffre = numeroOffre.toString(); }
}
