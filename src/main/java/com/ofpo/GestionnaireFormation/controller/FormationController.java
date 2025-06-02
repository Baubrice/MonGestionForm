package com.ofpo.GestionnaireFormation.controller;

import com.ofpo.GestionnaireFormation.model.Formation;
import com.ofpo.GestionnaireFormation.service.FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class FormationController {

    @Autowired
    private FormationService formationService;

    // Routes API
    @RestController
    @RequestMapping("/api/formations")
    public class FormationApiController {
        
        @GetMapping
        public ResponseEntity<?> listFormations() {
            return ResponseEntity.ok(formationService.findAll());
        }

        @PostMapping
        public ResponseEntity<?> createFormation(@RequestBody Formation formation) {
            try {
                Formation savedFormation = formationService.save(formation);
                return ResponseEntity.ok(savedFormation);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Erreur lors de la création de la formation: " + e.getMessage());
            }
        }

        @PutMapping("/{id}")
        public ResponseEntity<?> updateFormation(@PathVariable Long id, @RequestBody Formation formation) {
            try {
                formation.setId(id);
                Formation updatedFormation = formationService.save(formation);
                return ResponseEntity.ok(updatedFormation);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Erreur lors de la modification de la formation: " + e.getMessage());
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteFormation(@PathVariable Long id) {
            try {
                formationService.deleteById(id);
                return ResponseEntity.ok().build();
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Erreur lors de la suppression de la formation: " + e.getMessage());
            }
        }

        @PutMapping("/{id}/toggle-status")
        public ResponseEntity<?> toggleStatus(@PathVariable Long id) {
            try {
                Formation formation = formationService.findById(id)
                    .orElseThrow(() -> new RuntimeException("Formation non trouvée"));
                formation.setStatut(!formation.isStatut());
                Formation updatedFormation = formationService.save(formation);
                return ResponseEntity.ok(updatedFormation);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Erreur lors du changement de statut: " + e.getMessage());
            }
        }
    }

    // Routes de vue
    @GetMapping("/formations")
    public String viewFormations() {
        return "formations";
    }
}
