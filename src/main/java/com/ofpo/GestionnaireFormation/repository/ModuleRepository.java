package com.ofpo.GestionnaireFormation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<Module, Long> {
    Module findByLibelle(String libelle);
}
