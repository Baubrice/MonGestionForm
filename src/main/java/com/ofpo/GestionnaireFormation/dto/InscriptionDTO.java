package com.ofpo.GestionnaireFormation.dto;

import lombok.Data;

@Data
public class InscriptionDTO {
    private String email;
    private String pseudo;
    private String password;
    private String confirmPassword;
} 