package com.ofpo.GestionnaireFormation.model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "utilisateur")

public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String matricule;
    private String nom;
    private String prenom;
    private String adresseMail;
    private String adressePostal;
    private String codePostal;
    private String ville;
    private Boolean statut;

    @ManyToMany
    @JoinTable(name = "utilisateur_roles",
            joinColumns = @JoinColumn(name = "id_utilisateur"),
            inverseJoinColumns = @JoinColumn(name = "id_role"))

    private Set<Role> role = new LinkedHashSet<>();

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresseMail() {
        return adresseMail;
    }
    public void setAdresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
    }

    public String getAdressePostal() {
        return adressePostal;
    }
    public void setAdressePostal(String adressePostal) {
        this.adressePostal = adressePostal;
    }

    public String getCodePostal() {
        return codePostal;
    }
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }
    public void setVille(String ville) {
        this.ville = ville;
    }

    public Boolean getStatut() {
        return statut;
    }
    public void setStatut(Boolean statut) {
        this.statut = statut;
    }
}
