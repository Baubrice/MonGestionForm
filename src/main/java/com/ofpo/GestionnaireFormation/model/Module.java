package com.ofpo.GestionnaireFormation.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "module")
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "libelle", nullable = false)
    private String libelle;
    
    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "modules")
    private Set<Formation> formations = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "module_sequence",
            joinColumns = @JoinColumn(name = "id_module"),
            inverseJoinColumns = @JoinColumn(name = "id_sequence"))
    private Set<Sequence> sequences = new LinkedHashSet<>();

    public Set<Sequence> getSequences() {
        return sequences;
    }

    public void setSequences(Set<Sequence> sequences) {
        this.sequences = sequences;
    }

    public Set<Formation> getFormations() {
        return formations;
    }

    public void setFormations(Set<Formation> formations) {
        this.formations = formations;
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
}
