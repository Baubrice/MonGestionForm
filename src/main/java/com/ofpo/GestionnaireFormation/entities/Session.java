package com.ofpo.GestionnaireFormation.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * Entité représentant une session de formation dans le système.
 * Cette classe mappe la table des sessions dans la base de données.
 */
@Data
@Entity
@Table(name = "sessions")
public class Session {

    /**
     * Identifiant unique de la session.
     * Généré automatiquement par la base de données.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Formation associée à cette session.
     * Relation Many-to-One avec l'entité Formation.
     */
    @ManyToOne
    @JoinColumn(name = "formation_id", nullable = false)
    private Formation formation;

    /**
     * Date de début de la session.
     * Ne peut pas être null.
     */
    @Column(nullable = false)
    private LocalDate dateDebut;

    /**
     * Date de fin de la session.
     * Ne peut pas être null.
     */
    @Column(nullable = false)
    private LocalDate dateFin;

    /**
     * Nombre de places disponibles pour cette session.
     */
    private Integer nombrePlaces;

    /**
     * Liste des stagiaires inscrits à cette session.
     * Relation Many-to-Many avec l'entité Stagiaire.
     */
    @ManyToMany
    @JoinTable(
        name = "session_stagiaires",
        joinColumns = @JoinColumn(name = "session_id"),
        inverseJoinColumns = @JoinColumn(name = "stagiaire_id")
    )
    private List<Stagiaire> stagiaires;

    /**
     * Constructeur par défaut requis par JPA.
     */
    public Session() {
    }

    /**
     * Constructeur avec les champs obligatoires.
     * @param formation La formation associée
     * @param dateDebut Date de début de la session
     * @param dateFin Date de fin de la session
     */
    public Session(Formation formation, LocalDate dateDebut, LocalDate dateFin) {
        this.formation = formation;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }
} 