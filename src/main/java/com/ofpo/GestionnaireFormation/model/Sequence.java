package com.ofpo.GestionnaireFormation.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "sequence")
public class Sequence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "libelle", nullable = false)
    private String libelle;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "duree")
    private Integer duree;

    @ManyToMany(mappedBy = "sequences")
    private Set<Module> modules = new LinkedHashSet<>();

    public Set<Module> getModules() {
        return modules;
    }

    public void setModules(Set<Module> modules) {
        this.modules = modules;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }
}
