package com.ofpo.GestionnaireFormation.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Contrôleur principal de l'application.
 * Gère la navigation entre les différentes vues et coordonne les autres contrôleurs.
 */
@Component
public class MainController {

    // Références aux autres contrôleurs de l'application
    @Autowired
    private FormationUIController formationController;
    @Autowired
    private SessionUIController sessionController;
    @Autowired
    private StagiaireUIController stagiaireController;

    // Composant FXML principal de l'interface
    @FXML
    private TabPane mainTabPane;

    /**
     * Initialise le contrôleur après le chargement du FXML.
     * Configure les onglets et charge les données initiales.
     */
    @FXML
    public void initialize() {
        // Configuration des onglets
        setupTabs();
    }

    /**
     * Configure les onglets de l'application.
     * Associe chaque onglet à son contrôleur respectif.
     */
    private void setupTabs() {
        // L'initialisation des onglets est gérée par Spring et FXML
        // Les contrôleurs sont injectés automatiquement
    }
} 