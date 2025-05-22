package com.ofpo.GestionnaireFormation.dto;

import java.util.List;

public class UtilisateurDto {

    private String matricule;
    private String nom;
    private String prenom;
    private String adresseMail;
    private String adressePostal;
    private String codePostal;
    private String ville;

    public UtilisateurDto() {

        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.adresseMail = adresseMail;
        this.adressePostal = adressePostal;
        this.codePostal = codePostal;
        this.ville = ville;

    }

    public String getMatricule() { return matricule; }

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

    public String getAdressePostal(String adressePostal) {
        return this.adressePostal;
    }

    public void setAdressePostal(String adressePostal) {
        this.adressePostal = adressePostal;
    }

    public String getCodePostal(String codePostal) {
        return this.codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille(String ville) {
        return this.ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setRole(List<RoleDto> roleDtos) {  }
}
