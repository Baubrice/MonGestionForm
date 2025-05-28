package com.ofpo.GestionnaireFormation.repository;

import com.ofpo.GestionnaireFormation.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
    Module findByLibelle(String libelle);
}
