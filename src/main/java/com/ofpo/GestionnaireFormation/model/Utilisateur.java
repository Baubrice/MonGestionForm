package com.ofpo.GestionnaireFormation.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "utilisateur")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "matricule", unique = true)
    private String matricule;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "adresse_mail")
    private String adresseMail;

    @Column(name = "adresse_postal")
    private String adressePostal;

    @Column(name = "code_postal")
    private String codePostal;
    @Column(name = "ville")
    private String ville;
    @Column(name = "statut")
    private Boolean statut;

    @ManyToMany
    @JoinTable(
        name = "utilisateur_roles",
        joinColumns = @JoinColumn(name = "id_utilisateur"),
        inverseJoinColumns = @JoinColumn(name = "id_role")
    )
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(mappedBy = "utilisateurs")
    private Set<Formation> formations = new HashSet<>();

    public String getCodePostal() {
        return this.codePostal;
    }
    public String getVille() {
        return this.ville;
    }
    public Set<Role> getRoles() {
        return this.roles;
    }

    public Arrays getRole() {
        return null;
    }

    // Getters et Setters générés par @Data
}
