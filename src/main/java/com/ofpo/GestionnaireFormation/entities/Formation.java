package com.ofpo.GestionnaireFormation.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * Entité représentant une formation dans le système.
 * Cette classe mappe la table des formations dans la base de données.
 */
@Data
@Entity
@Table(name = "formations")
public class Formation {

    /**
     * Identifiant unique de la formation.
     * Généré automatiquement par la base de données.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Titre de la formation.
     * Ne peut pas être null.
     */
    @Column(nullable = false)
    private String titre;

    /**
     * Code unique de la formation.
     * Ne peut pas être null et doit être unique.
     */
    @Column(nullable = false, unique = true)
    private String code;

    /**
     * Description détaillée de la formation.
     */
    @Column(length = 1000)
    private String description;

    /**
     * Prix de la formation.
     */
    private Double prix;

    /**
     * Durée de la formation en heures.
     */
    private Integer duree;

    /**
     * Liste des sessions associées à cette formation.
     * Relation One-to-Many avec l'entité Session.
     */
    @OneToMany(mappedBy = "formation", cascade = CascadeType.ALL)
    private List<Session> sessions;

    /**
     * Constructeur par défaut requis par JPA.
     */
    public Formation() {
    }

    /**
     * Constructeur avec les champs obligatoires.
     * @param titre Titre de la formation
     * @param description Description de la formation
     */
    public Formation(String titre, String description) {
        this.titre = titre;
        this.description = description;
    }
} 