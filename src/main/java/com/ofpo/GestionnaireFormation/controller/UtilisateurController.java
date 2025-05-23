package com.ofpo.GestionnaireFormation.controller;

import com.ofpo.GestionnaireFormation.model.Utilisateur;
import com.ofpo.GestionnaireFormation.repository.UtilisateurRepository;
import com.ofpo.GestionnaireFormation.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/demo")
    public String demo(){
        return "ghjghj";
    }

    @GetMapping("/")
    public List<Utilisateur> findAll() {return this.utilisateurRepository.findAll();}

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
