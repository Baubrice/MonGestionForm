package com.ofpo.GestionnaireFormation.repository;

import com.ofpo.GestionnaireFormation.model.Sequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface SequenceRepository extends JpaRepository<Sequence, Long> {
    Sequence findByLibelle(String libelle);
    
    @Query("SELECT s FROM Sequence s JOIN s.modules m WHERE m.id = :moduleId")
    Optional<Sequence> findByModuleId(@Param("moduleId") Long moduleId);
}
