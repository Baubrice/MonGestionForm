package com.ofpo.GestionnaireFormation.model;

import jakarta.persistence.*;

@Entity
@Table(name = "centre")
public class Centre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}