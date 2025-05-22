package com.ofpo.GestionnaireFormation.controller;

import com.ofpo.GestionnaireFormation.dto.RoleDto;
import com.ofpo.GestionnaireFormation.dto.UtilisateurDto;
import com.ofpo.GestionnaireFormation.model.Utilisateur;
import com.ofpo.GestionnaireFormation.repository.UtilisateurRepository;
import com.ofpo.GestionnaireFormation.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/utilisateur")
public class UtilisateurController {

    @Autowired
    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurService utilisateurService = null;
    private Long matricule;

    public UtilisateurController(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

//    @GetMapping("/demo")
//    public String demo(){
//        return "test de JSON";
//    }

    @GetMapping("/demo")
        public List<String> demo(){
        List<String> demos = new ArrayList<>();
        demos.add("foo");
        demos.add("bar");
        demos.add("foobar");
        return demos.stream().filter(nom -> nom.length() <= 6).toList();
    }

//    @GetMapping("/")
//    public List<Utilisateur> findAll() {return this.utilisateurRepository.findAll();}

    @GetMapping("/")
    public List<UtilisateurDto> findAll() {
        List<Utilisateur> utilisateurs=  this.utilisateurRepository.findAll();

        return utilisateurs.stream().map(utilisateur -> {
    // dans l'utilisateur va chercher uniquement les infos à afficher en JSON
            UtilisateurDto dto = new UtilisateurDto();
            dto.setMatricule(utilisateur.getMatricule());
            dto.setNom(utilisateur.getNom());
            dto.setPrenom(utilisateur.getPrenom());
            dto.setAdresseMail(utilisateur.getAdresseMail());
            dto.getAdressePostal(utilisateur.getAdressePostal());
            dto.getCodePostal(utilisateur.getCodePostal());
            dto.getVille(utilisateur.getVille());

    // dans role aller chercher le libelle
            List<RoleDto> roleDtos = utilisateur.getRole().stream()
                    .map(role -> new RoleDto(role.getLibelle()))
                    .toList();
            dto.setRole(roleDtos);
            return dto;
        })
                .toList();
    }

    @GetMapping("/{matricule}")
    public Utilisateur findByMatricule(@PathVariable String matricule) {
        return this.utilisateurRepository.findByMatricule(matricule);
    }
    @PostMapping("/ajouter")
    public void add(@RequestBody Utilisateur utilisateur){
        utilisateurService.createUtilisateur(utilisateur);
    }

    @PutMapping("/modifier")
    public void updateUtilisateur(@PathVariable Long matricule, @RequestBody Utilisateur utilisateur){
        this.matricule = matricule;
        utilisateurService.updateUtilisateur(utilisateur);
    }

    @DeleteMapping("/supprimer")
    public void deleteUtilisateur(@PathVariable Long id){
        utilisateurService.deleteUtilisateur(id);
    }

    @PutMapping("/modifier/{statut}")
    public String updateStatut(@PathVariable boolean statut) {
        utilisateurService.updateStatut(statut);
        return "Statut mis à jour !";
    }





}
