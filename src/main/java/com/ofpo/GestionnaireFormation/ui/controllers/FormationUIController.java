package com.ofpo.GestionnaireFormation.ui.controllers;

import com.ofpo.GestionnaireFormation.entities.Formation;
import com.ofpo.GestionnaireFormation.service.FormationService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Contrôleur pour la gestion des formations dans l'interface utilisateur.
 * Ce contrôleur gère toutes les interactions avec la vue des formations.
 */
@Component
public class FormationUIController {

    // Service pour la gestion des formations
    @Autowired
    private FormationService formationService;

    // Liste observable des formations pour la TableView
    private ObservableList<Formation> formations = FXCollections.observableArrayList();

    // Composants FXML de l'interface utilisateur
    @FXML
    private TableView<Formation> formationTable;
    @FXML
    private TableColumn<Formation, String> titreColumn;
    @FXML
    private TableColumn<Formation, String> descriptionColumn;
    @FXML
    private TableColumn<Formation, Double> prixColumn;
    @FXML
    private TableColumn<Formation, Integer> dureeColumn;
    @FXML
    private TextField titreField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private TextField prixField;
    @FXML
    private TextField dureeField;

    /**
     * Initialise le contrôleur après le chargement du FXML.
     * Configure les colonnes de la table et charge les données initiales.
     */
    @FXML
    public void initialize() {
        // Configuration des colonnes de la table
        titreColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        dureeColumn.setCellValueFactory(new PropertyValueFactory<>("duree"));

        // Association de la liste observable à la table
        formationTable.setItems(formations);

        // Chargement des données initiales
        loadFormations();
    }

    /**
     * Charge la liste des formations depuis le service et l'affiche dans la table.
     */
    private void loadFormations() {
        formations.clear();
        formations.addAll(formationService.findAll());
    }

    /**
     * Gère l'ajout d'une nouvelle formation.
     * Récupère les données des champs et crée une nouvelle formation.
     */
    public void handleAddFormation() {
        // Création d'une nouvelle formation avec les données des champs
        Formation formation = new Formation();
        formation.setTitre(titreField.getText());
        formation.setDescription(descriptionField.getText());
        formation.setPrix(Double.parseDouble(prixField.getText()));
        formation.setDuree(Integer.parseInt(dureeField.getText()));

        // Sauvegarde de la formation via le service
        formationService.save(formation);

        // Rechargement de la liste des formations
        loadFormations();

        // Réinitialisation des champs
        clearFields();
    }

    /**
     * Gère la modification d'une formation existante.
     */
    public void handleModifyFormation() {
        Formation selectedFormation = formationTable.getSelectionModel().getSelectedItem();
        if (selectedFormation != null) {
            selectedFormation.setTitre(titreField.getText());
            selectedFormation.setDescription(descriptionField.getText());
            selectedFormation.setPrix(Double.parseDouble(prixField.getText()));
            selectedFormation.setDuree(Integer.parseInt(dureeField.getText()));
            
            formationService.save(selectedFormation);
            loadFormations();
            clearFields();
        }
    }

    /**
     * Gère la suppression d'une formation sélectionnée.
     */
    public void handleDeleteFormation() {
        Formation selectedFormation = formationTable.getSelectionModel().getSelectedItem();
        if (selectedFormation != null) {
            formationService.deleteFormation(selectedFormation.getId());
            loadFormations();
        }
    }

    /**
     * Réinitialise tous les champs du formulaire.
     */
    private void clearFields() {
        titreField.clear();
        descriptionField.clear();
        prixField.clear();
        dureeField.clear();
    }
} 