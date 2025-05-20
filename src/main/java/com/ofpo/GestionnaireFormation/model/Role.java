package com.ofpo.GestionnaireFormation.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libele;
    private Boolean status;

    @Column(name = "libelle", nullable = false, length = 50)
    private String libelle;

    @ColumnDefault("0")
    @Column(name = "statut", nullable = false)
    private Boolean statut = false;

    @ManyToMany(mappedBy = "roles")
    private Set<Utilisateur> utilisateurs = new LinkedHashSet<>();

    public Set<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(Set<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public Boolean getStatut() {
        return statut;
    }

    public void setStatut(Boolean statut) {
        this.statut = statut;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getLibele() { return libele;}
    public void setLibele(String libele) { this.libele = libele;}

    public Boolean getStatus() {return status;}
    public void setStatus(Boolean status) {this.status = status;}
}