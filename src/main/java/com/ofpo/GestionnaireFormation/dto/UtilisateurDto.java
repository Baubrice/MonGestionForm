package com.ofpo.GestionnaireFormation.dto;

import java.util.List;

import lombok.Data;

@Data
public class UtilisateurDto {
    private Long id;
    private String email;
    private String nom;
    private String prenom;
    private String password;
    private String adresseMail;
    private String adressePostal;
    private String codePostal;
    private String ville;
    private List<RoleDto> roles;
}
