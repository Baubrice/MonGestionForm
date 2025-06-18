package com.ofpo.GestionnaireFormation.security;

import com.auth0.spring.security.api.JwtWebSecurityConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Auth0Config {

    @Value("${auth0.apiAudience}")
    private String apiAudience;

    @Value("${auth0.issuer}")
    private String issuer;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        JwtWebSecurityConfigurer
                .forRS256(apiAudience, issuer)
                .configure(http)
                .authorizeHttpRequests(auth -> auth
                        // Endpoints publics
                        .requestMatchers(HttpMethod.GET, "/api/public/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                        
                        // Endpoints pour les formations
                        .requestMatchers(HttpMethod.GET, "/api/formations/**").hasAuthority("read:formations")
                        .requestMatchers(HttpMethod.POST, "/api/formations/**").hasAuthority("create:formations")
                        .requestMatchers(HttpMethod.PUT, "/api/formations/**").hasAuthority("update:formations")
                        .requestMatchers(HttpMethod.DELETE, "/api/formations/**").hasAuthority("delete:formations")
                        
                        // Endpoints pour les sessions
                        .requestMatchers(HttpMethod.GET, "/api/sessions/**").hasAuthority("read:sessions")
                        .requestMatchers(HttpMethod.POST, "/api/sessions/**").hasAuthority("create:sessions")
                        .requestMatchers(HttpMethod.PUT, "/api/sessions/**").hasAuthority("update:sessions")
                        .requestMatchers(HttpMethod.DELETE, "/api/sessions/**").hasAuthority("delete:sessions")
                        
                        // Endpoints pour les stagiaires
                        .requestMatchers(HttpMethod.GET, "/api/stagiaires/**").hasAuthority("read:stagiaires")
                        .requestMatchers(HttpMethod.POST, "/api/stagiaires/**").hasAuthority("create:stagiaires")
                        .requestMatchers(HttpMethod.PUT, "/api/stagiaires/**").hasAuthority("update:stagiaires")
                        .requestMatchers(HttpMethod.DELETE, "/api/stagiaires/**").hasAuthority("delete:stagiaires")
                        
                        // Endpoints pour les statistiques
                        .requestMatchers(HttpMethod.GET, "/api/statistiques/**").hasAuthority("read:statistiques")
                        
                        // Endpoints pour les utilisateurs
                        .requestMatchers(HttpMethod.GET, "/api/utilisateurs/**").hasAuthority("read:utilisateurs")
                        .requestMatchers(HttpMethod.POST, "/api/utilisateurs/**").hasAuthority("create:utilisateurs")
                        .requestMatchers(HttpMethod.PUT, "/api/utilisateurs/**").hasAuthority("update:utilisateurs")
                        .requestMatchers(HttpMethod.DELETE, "/api/utilisateurs/**").hasAuthority("delete:utilisateurs")
                        
                        // Tous les autres endpoints nécessitent une authentification
                        .anyRequest().authenticated()
                );
        return http.build();
    }
} 