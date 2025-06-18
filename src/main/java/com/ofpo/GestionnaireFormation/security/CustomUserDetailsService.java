package com.ofpo.GestionnaireFormation.security;

import com.ofpo.GestionnaireFormation.model.Utilisateur;
import com.ofpo.GestionnaireFormation.service.UtilisateurService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UtilisateurService utilisateurService;

    public CustomUserDetailsService(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Override
    public UserDetails loadUserByUsername(String matricule) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurService.findByMatricule(matricule);
        if (utilisateur == null) {
            throw new UsernameNotFoundException("Utilisateur non trouvé avec le matricule: " + matricule);
        }

        var authorities = utilisateur.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getLibelle()))
                .collect(Collectors.toList());

        return new User(
            utilisateur.getMatricule(),
            utilisateur.getMotDePasse(),
            authorities
        );
    }
} 