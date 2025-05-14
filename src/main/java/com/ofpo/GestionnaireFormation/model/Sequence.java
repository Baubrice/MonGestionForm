package com.ofpo.GestionnaireFormation.model;


import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "sequence")
public class Sequence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;

    @ManyToMany(mappedBy = "sequences")
    private Set<Module> modules = new LinkedHashSet<>();

    public Set<Module> getModules() {
        return modules;
    }

    public void setModules(Set<Module> modules) {
        this.modules = modules;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getLibelle() {return libelle;}
    public void setLibelle(String libelle) {this.libelle = libelle;}

}
