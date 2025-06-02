package com.ofpo.GestionnaireFormation.controller;

import com.ofpo.GestionnaireFormation.dto.RoleDto;
import com.ofpo.GestionnaireFormation.dto.UtilisateurDto;
import com.ofpo.GestionnaireFormation.model.Utilisateur;
import com.ofpo.GestionnaireFormation.repository.UtilisateurRepository;
import com.ofpo.GestionnaireFormation.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UtilisateurController {

    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurRepository utilisateurRepository, UtilisateurService utilisateurService) {
        this.utilisateurRepository = utilisateurRepository;
        this.utilisateurService = utilisateurService;
    }

    // Routes API
    @RestController
    @RequestMapping("/api/utilisateurs")
    public class UtilisateurApiController {

        @GetMapping
        public ResponseEntity<List<UtilisateurDto>> findAll() {
            List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
            List<UtilisateurDto> utilisateurDtos = utilisateurs.stream()
                .map(this::convertToDto)
                .toList();
            return ResponseEntity.ok(utilisateurDtos);
        }

        @GetMapping("/{email}")
        public ResponseEntity<UtilisateurDto> findByEmail(@PathVariable String email) {
            return utilisateurRepository.findByEmail(email)
                .map(utilisateur -> ResponseEntity.ok(convertToDto(utilisateur)))
                .orElse(ResponseEntity.notFound().build());
        }

        @GetMapping("/recherche/{nom}")
        public ResponseEntity<List<UtilisateurDto>> findByNom(@PathVariable String nom) {
            List<Utilisateur> utilisateurs = utilisateurRepository.findByNomContainingIgnoreCase(nom);
            List<UtilisateurDto> utilisateurDtos = utilisateurs.stream()
                .map(this::convertToDto)
                .toList();
            return ResponseEntity.ok(utilisateurDtos);
        }

        @PostMapping
        public ResponseEntity<UtilisateurDto> create(@RequestBody Utilisateur utilisateur) {
            Utilisateur savedUtilisateur = utilisateurService.save(utilisateur);
            return ResponseEntity.ok(convertToDto(savedUtilisateur));
        }

        @PutMapping("/{email}")
        public ResponseEntity<UtilisateurDto> update(@PathVariable String email, @RequestBody Utilisateur utilisateur) {
            return utilisateurRepository.findByEmail(email)
                .map(existingUtilisateur -> {
                    utilisateur.setId(existingUtilisateur.getId());
                    Utilisateur updatedUtilisateur = utilisateurService.save(utilisateur);
                    return ResponseEntity.ok(convertToDto(updatedUtilisateur));
                })
                .orElse(ResponseEntity.notFound().build());
        }

        @DeleteMapping("/{email}")
        public ResponseEntity<Void> delete(@PathVariable String email) {
            return utilisateurRepository.findByEmail(email)
                .map(utilisateur -> {
                    utilisateurService.deleteById(utilisateur.getId());
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
        }

        private UtilisateurDto convertToDto(Utilisateur utilisateur) {
            UtilisateurDto dto = new UtilisateurDto();
            dto.setId(utilisateur.getId());
            dto.setEmail(utilisateur.getEmail());
            dto.setNom(utilisateur.getNom());
            dto.setPrenom(utilisateur.getPrenom());
            dto.setAdresseMail(utilisateur.getAdresseMail());
            dto.setAdressePostal(utilisateur.getAdressePostal());
            dto.setCodePostal(utilisateur.getCodePostal());
            dto.setVille(utilisateur.getVille());

            List<RoleDto> roleDtos = utilisateur.getRoles().stream()
                .map(role -> {
                    RoleDto roleDto = new RoleDto();
                    roleDto.setId(role.getId());
                    roleDto.setLibelle(role.getLibelle());
                    return roleDto;
                })
                .toList();
            dto.setRoles(roleDtos);
            return dto;
        }
    }

    // Routes de vue
    @GetMapping("/utilisateurs")
    public String viewUtilisateurs() {
        return "utilisateurs";
    }
}
