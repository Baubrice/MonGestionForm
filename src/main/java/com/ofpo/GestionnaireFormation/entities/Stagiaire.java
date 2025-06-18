package com.ofpo.GestionnaireFormation.entities;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entité représentant un stagiaire dans le système.
 * Cette classe mappe la table des stagiaires dans la base de données.
 */
@Data
@Entity
@Table(name = "stagiaires")
public class Stagiaire {

    /**
     * Identifiant unique du stagiaire.
     * Généré automatiquement par la base de données.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nom du stagiaire.
     * Ne peut pas être null.
     */
    @Column(nullable = false)
    private String nom;

    /**
     * Prénom du stagiaire.
     * Ne peut pas être null.
     */
    @Column(nullable = false)
    private String prenom;

    /**
     * Adresse email du stagiaire.
     * Doit être unique dans la base de données.
     */
    @Column(unique = true)
    private String email;

    /**
     * Numéro de téléphone du stagiaire.
     */
    private String telephone;

    /**
     * Adresse postale du stagiaire.
     */
    private String adresse;

    /**
     * Date de naissance du stagiaire.
     */
    private String dateNaissance;

    /**
     * Constructeur par défaut requis par JPA.
     */
    public Stagiaire() {
    }

    /**
     * Constructeur avec les champs obligatoires.
     * @param nom Nom du stagiaire
     * @param prenom Prénom du stagiaire
     */
    public Stagiaire(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }
} 