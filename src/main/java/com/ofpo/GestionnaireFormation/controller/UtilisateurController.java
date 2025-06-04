package com.ofpo.GestionnaireFormation.controller;

import com.ofpo.GestionnaireFormation.dto.RoleDto;
import com.ofpo.GestionnaireFormation.dto.UtilisateurDto;
import com.ofpo.GestionnaireFormation.model.Utilisateur;
import com.ofpo.GestionnaireFormation.repository.UtilisateurRepository;
import com.ofpo.GestionnaireFormation.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/utilisateur")
public class UtilisateurController {

    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurRepository utilisateurRepository, UtilisateurService utilisateurService) {
        this.utilisateurRepository = utilisateurRepository;
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/demo")
    public List<String> demo() {
        List<String> demos = new ArrayList<>();
        demos.add("foo");
        demos.add("bar");
        demos.add("foobar");
        return demos.stream().filter(nom -> nom.length() <= 6).toList();
    }

//    @GetMapping("/")
//    public List<Utilisateur> findAll() {return this.utilisateurRepository.findAll();}

    @GetMapping("/")
    public ResponseEntity<List<UtilisateurDto>> findAll() {
        List<Utilisateur> utilisateurs = this.utilisateurRepository.findAll();
        List<UtilisateurDto> utilisateurDtos = utilisateurs.stream()
            .map(utilisateur -> {
                UtilisateurDto dto = new UtilisateurDto();
                dto.setMatricule(utilisateur.getMatricule());
                dto.setNom(utilisateur.getNom());
                dto.setPrenom(utilisateur.getPrenom());
                dto.setAdresseMail(utilisateur.getAdresseMail());
                dto.setAdressePostal(utilisateur.getAdressePostal());
                dto.setCodePostal(utilisateur.getCodePostal());
                dto.setVille(utilisateur.getVille());

                List<RoleDto> roleDtos = utilisateur.getRoles().stream()
                    .map(role -> new RoleDto(role.getLibelle()))
                    .toList();
                dto.setRole(roleDtos);
                return dto;
            })
            .toList();
        return ResponseEntity.ok(utilisateurDtos);
    }

    @GetMapping("/{matricule}")
    public ResponseEntity<UtilisateurDto> findByMatricule(@PathVariable String matricule) {
        Utilisateur utilisateur = this.utilisateurRepository.findByMatricule(matricule);
        if (utilisateur == null) {
            return ResponseEntity.notFound().build();
        }
        
        UtilisateurDto dto = new UtilisateurDto();
        dto.setMatricule(utilisateur.getMatricule());
        dto.setNom(utilisateur.getNom());
        dto.setPrenom(utilisateur.getPrenom());
        dto.setAdresseMail(utilisateur.getAdresseMail());
        dto.setAdressePostal(utilisateur.getAdressePostal());
        dto.setCodePostal(utilisateur.getCodePostal());
        dto.setVille(utilisateur.getVille());

        List<RoleDto> roleDtos = utilisateur.getRoles().stream()
            .map(role -> new RoleDto(role.getLibelle()))
            .toList();
        dto.setRole(roleDtos);
        
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/ajouter")
    public ResponseEntity<UtilisateurDto> add(@RequestBody Utilisateur utilisateur) {
        Utilisateur savedUtilisateur = utilisateurService.createUtilisateur(utilisateur);
        
        UtilisateurDto dto = new UtilisateurDto();
        dto.setMatricule(savedUtilisateur.getMatricule());
        dto.setNom(savedUtilisateur.getNom());
        dto.setPrenom(savedUtilisateur.getPrenom());
        dto.setAdresseMail(savedUtilisateur.getAdresseMail());
        dto.setAdressePostal(savedUtilisateur.getAdressePostal());
        dto.setCodePostal(savedUtilisateur.getCodePostal());
        dto.setVille(savedUtilisateur.getVille());

        List<RoleDto> roleDtos = savedUtilisateur.getRoles().stream()
            .map(role -> new RoleDto(role.getLibelle()))
            .toList();
        dto.setRole(roleDtos);
        
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/modifier/{matricule}")
    public ResponseEntity<UtilisateurDto> updateUtilisateur(@PathVariable String matricule, @RequestBody Utilisateur utilisateur) {
        Utilisateur updatedUtilisateur = utilisateurService.updateUtilisateur(matricule, utilisateur);
        if (updatedUtilisateur == null) {
            return ResponseEntity.notFound().build();
        }
        
        UtilisateurDto dto = new UtilisateurDto();
        dto.setMatricule(updatedUtilisateur.getMatricule());
        dto.setNom(updatedUtilisateur.getNom());
        dto.setPrenom(updatedUtilisateur.getPrenom());
        dto.setAdresseMail(updatedUtilisateur.getAdresseMail());
        dto.setAdressePostal(updatedUtilisateur.getAdressePostal());
        dto.setCodePostal(updatedUtilisateur.getCodePostal());
        dto.setVille(updatedUtilisateur.getVille());

        List<RoleDto> roleDtos = updatedUtilisateur.getRoles().stream()
            .map(role -> new RoleDto(role.getLibelle()))
            .toList();
        dto.setRole(roleDtos);
        
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/supprimer/{matricule}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable String matricule) {
        utilisateurService.deleteUtilisateur(matricule);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/modifier/statut/{matricule}")
    public ResponseEntity<String> updateStatut(@PathVariable String matricule, @RequestParam boolean statut) {
        utilisateurService.updateStatut(matricule, statut);
        return ResponseEntity.ok("Statut mis à jour avec succès !");
    }

    public UtilisateurRepository getUtilisateurRepository() {
        return utilisateurRepository;
    }

    public UtilisateurService getUtilisateurService() {
        return utilisateurService;
    }

    /**
 * Tester les commandes javadoc
 */

}
