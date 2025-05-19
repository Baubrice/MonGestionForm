package com.ofpo.GestionnaireFormation.repository;

import com.ofpo.GestionnaireFormation.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolleRepository extends JpaRepository<Role, Long> {
    Role findByLibelle(String libelle);
}
