package com.ofpo.GestionnaireFormation.repository;

import com.ofpo.GestionnaireFormation.entities.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormationRepository extends JpaRepository<Formation, Long> {
    Formation findByCode(String code);
    boolean existsByCode(String code);
}
