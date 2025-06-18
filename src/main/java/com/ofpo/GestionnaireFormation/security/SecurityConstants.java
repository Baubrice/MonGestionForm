package com.ofpo.GestionnaireFormation.security;

public class SecurityConstants {
    // Rôles
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_FORMATEUR = "ROLE_FORMATEUR";
    public static final String ROLE_STAGIAIRE = "ROLE_STAGIAIRE";

    // Scopes pour les formations
    public static final String SCOPE_READ_FORMATIONS = "read:formations";
    public static final String SCOPE_CREATE_FORMATIONS = "create:formations";
    public static final String SCOPE_UPDATE_FORMATIONS = "update:formations";
    public static final String SCOPE_DELETE_FORMATIONS = "delete:formations";

    // Scopes pour les sessions
    public static final String SCOPE_READ_SESSIONS = "read:sessions";
    public static final String SCOPE_CREATE_SESSIONS = "create:sessions";
    public static final String SCOPE_UPDATE_SESSIONS = "update:sessions";
    public static final String SCOPE_DELETE_SESSIONS = "delete:sessions";

    // Scopes pour les stagiaires
    public static final String SCOPE_READ_STAGIAIRES = "read:stagiaires";
    public static final String SCOPE_CREATE_STAGIAIRES = "create:stagiaires";
    public static final String SCOPE_UPDATE_STAGIAIRES = "update:stagiaires";
    public static final String SCOPE_DELETE_STAGIAIRES = "delete:stagiaires";

    // Scopes pour les statistiques
    public static final String SCOPE_READ_STATISTIQUES = "read:statistiques";

    // Scopes pour les utilisateurs
    public static final String SCOPE_READ_UTILISATEURS = "read:utilisateurs";
    public static final String SCOPE_CREATE_UTILISATEURS = "create:utilisateurs";
    public static final String SCOPE_UPDATE_UTILISATEURS = "update:utilisateurs";
    public static final String SCOPE_DELETE_UTILISATEURS = "delete:utilisateurs";

    // Permissions par rôle
    public static final String[] ADMIN_PERMISSIONS = {
        SCOPE_READ_FORMATIONS, SCOPE_CREATE_FORMATIONS, SCOPE_UPDATE_FORMATIONS, SCOPE_DELETE_FORMATIONS,
        SCOPE_READ_SESSIONS, SCOPE_CREATE_SESSIONS, SCOPE_UPDATE_SESSIONS, SCOPE_DELETE_SESSIONS,
        SCOPE_READ_STAGIAIRES, SCOPE_CREATE_STAGIAIRES, SCOPE_UPDATE_STAGIAIRES, SCOPE_DELETE_STAGIAIRES,
        SCOPE_READ_STATISTIQUES,
        SCOPE_READ_UTILISATEURS, SCOPE_CREATE_UTILISATEURS, SCOPE_UPDATE_UTILISATEURS, SCOPE_DELETE_UTILISATEURS
    };

    public static final String[] FORMATEUR_PERMISSIONS = {
        SCOPE_READ_FORMATIONS,
        SCOPE_READ_SESSIONS, SCOPE_CREATE_SESSIONS, SCOPE_UPDATE_SESSIONS,
        SCOPE_READ_STAGIAIRES,
        SCOPE_READ_STATISTIQUES
    };

    public static final String[] STAGIAIRE_PERMISSIONS = {
        SCOPE_READ_FORMATIONS,
        SCOPE_READ_SESSIONS,
        SCOPE_READ_STAGIAIRES
    };
} 