package com.ofpo.GestionnaireFormation.service;

import com.ofpo.GestionnaireFormation.dto.InscriptionDTO;
import com.ofpo.GestionnaireFormation.model.Role;
import com.ofpo.GestionnaireFormation.model.Utilisateur;
import com.ofpo.GestionnaireFormation.repository.RoleRepository;
import com.ofpo.GestionnaireFormation.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec l'email: " + email));
        
        return new User(
            utilisateur.getEmail(),
            utilisateur.getPassword(),
            List.of(new SimpleGrantedAuthority("ROLE_" + utilisateur.getRole().getLibelle().toUpperCase()))
        );
    }

    public Utilisateur inscrire(InscriptionDTO inscriptionDTO) {
        if (!inscriptionDTO.getPassword().equals(inscriptionDTO.getConfirmPassword())) {
            throw new RuntimeException("Les mots de passe ne correspondent pas");
        }

        if (utilisateurRepository.findByEmail(inscriptionDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Cet email est déjà utilisé");
        }

        if (utilisateurRepository.findByPseudo(inscriptionDTO.getPseudo()).isPresent()) {
            throw new RuntimeException("Ce pseudo est déjà utilisé");
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail(inscriptionDTO.getEmail());
        utilisateur.setPseudo(inscriptionDTO.getPseudo());
        utilisateur.setPassword(inscriptionDTO.getPassword());
        utilisateur.setNom(""); // Valeur par défaut
        utilisateur.setPrenom(""); // Valeur par défaut

        // Par défaut, on attribue le rôle "stagiaire"
        Role roleStagiaire = roleRepository.findByLibelle("stagiaire")
                .orElseThrow(() -> new RuntimeException("Rôle stagiaire non trouvé"));
        utilisateur.setRole(roleStagiaire);

        return utilisateurRepository.save(utilisateur);
    }
} 