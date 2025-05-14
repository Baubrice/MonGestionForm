package com.ofpo.GestionnaireFormation.controller;

import com.ofpo.GestionnaireFormation.model.Utilisateur;
import com.ofpo.GestionnaireFormation.repository.UtilisateurRepository;
import com.ofpo.GestionnaireFormation.service.UtilisateurService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateur")
public class UtilisateurController {

    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/demo")
    public String demo(){
        return "ghjghj";
    }

    @GetMapping("/")
    public List<Utilisateur> findAll() {return this.utilisateurRepository.findAll();}

    @GetMapping("/{matricule}")
    public Utilisateur findByMatricule(@PathVariable String matricule) {
        // retourner un utilisateur via un numéro de matricule
        return this.utilisateurRepository.findByMatricule(matricule);
    }

    @PostMapping("/ajouter")
    public void add(@RequestBody Utilisateur utilisateur){
        return utilisateurService.create(utilisateur);
        // ajouter un utilisateur en base de données
    }

    @PutMapping("/modifier")
    public void update(@PathVariable Long matricule, @RequestBody Utilisateur utilisateur){
        // @RequestBody convertir automatiquement le corps d'une requête HTTP en un objet Java. Elle est particulièrement utile pour manipuler des données envoyées en JSON ou en XML dans une API REST
        return utilisateurService.update(utilisateur);
        // modifier un utilisateur en base de données
    }

    @DeleteMapping("/supprimer")
    public void delete(@PathVariable Long matricule){
        // @PathVariable extraire des valeurs de l'URL et les utiliser comme paramètres d'une méthode de contrôleur. Elle est particulièrement utile lorsque tu veux récupérer une partie d'un chemin dynamique dans une requête HTTP.
        return utilisateurService.delete(utilisateur);
        // supprime un utilisateur en base de données
    }

    @PutMapping("/supprimer")
    public void updateStatut(@PathVariable Long matricule){
        return utilisateurService.updateSatut(utilisateur);
        // desactive un utilisateur en base de données
    }



}
