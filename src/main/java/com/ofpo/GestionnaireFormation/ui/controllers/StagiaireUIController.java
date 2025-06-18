package com.ofpo.GestionnaireFormation.ui.controllers;

import com.ofpo.GestionnaireFormation.entities.Stagiaire;
import com.ofpo.GestionnaireFormation.service.StagiaireService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;

/**
 * Contrôleur pour la gestion des stagiaires dans l'interface utilisateur.
 * Ce contrôleur gère toutes les interactions avec la vue des stagiaires.
 */
@Controller
public class StagiaireUIController {

    // Service pour la gestion des stagiaires
    @Autowired
    private StagiaireService stagiaireService;

    // Liste observable des stagiaires pour la TableView
    private ObservableList<Stagiaire> stagiaires = FXCollections.observableArrayList();

    // Composants FXML de l'interface utilisateur
    @FXML
    private TableView<Stagiaire> stagiaireTable;
    @FXML
    private TableColumn<Stagiaire, Long> idColumn;
    @FXML
    private TableColumn<Stagiaire, String> nomColumn;
    @FXML
    private TableColumn<Stagiaire, String> prenomColumn;
    @FXML
    private TableColumn<Stagiaire, String> emailColumn;
    @FXML
    private TableColumn<Stagiaire, String> telephoneColumn;
    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField telephoneField;

    /**
     * Initialise le contrôleur après le chargement du FXML.
     * Configure les colonnes de la table et charge les données initiales.
     */
    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        
        refreshStagiaireTable();
    }

    /**
     * Charge la liste des stagiaires depuis le service.
     */
    private void refreshStagiaireTable() {
        stagiaires.clear();
        stagiaires.addAll(stagiaireService.getAllStagiaires());
        stagiaireTable.setItems(stagiaires);
    }

    /**
     * Gère l'ajout d'un nouveau stagiaire.
     */
    @FXML
    public void handleAddStagiaire() {
        try {
            Stagiaire stagiaire = new Stagiaire();
            stagiaire.setNom(nomField.getText());
            stagiaire.setPrenom(prenomField.getText());
            stagiaire.setEmail(emailField.getText());
            stagiaire.setTelephone(telephoneField.getText());
            
            stagiaireService.saveStagiaire(stagiaire);
            refreshStagiaireTable();
            clearFields();
        } catch (Exception e) {
            showError("Erreur lors de l'ajout du stagiaire", e.getMessage());
        }
    }

    /**
     * Gère la suppression d'un stagiaire sélectionné.
     */
    @FXML
    public void handleDeleteStagiaire() {
        Stagiaire selectedStagiaire = stagiaireTable.getSelectionModel().getSelectedItem();
        if (selectedStagiaire != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText("Supprimer le stagiaire");
            alert.setContentText("Êtes-vous sûr de vouloir supprimer ce stagiaire ?");
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                stagiaireService.deleteStagiaire(selectedStagiaire.getId());
                refreshStagiaireTable();
            }
        } else {
            showError("Erreur", "Veuillez sélectionner un stagiaire à supprimer");
        }
    }

    /**
     * Réinitialise tous les champs de texte du formulaire.
     */
    private void clearFields() {
        nomField.clear();
        prenomField.clear();
        emailField.clear();
        telephoneField.clear();
    }

    private void showError(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
} 