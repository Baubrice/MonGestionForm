package com.ofpo.GestionnaireFormation.ui.controllers;

import com.ofpo.GestionnaireFormation.entities.Stagiaire;
import com.ofpo.GestionnaireFormation.service.StagiaireService;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("stagiaireUiController")
public class StagiaireController {
    @Autowired
    private StagiaireService stagiaireService;
    
    private Button btnNouveauStagiaire;
    private Button btnModifier;
    private Button btnSupprimer;
    private TableView<Object> stagiaireTable;
    private TextField[] detailFields;

    public void initialize(Button btnNouveauStagiaire, Button btnModifier, Button btnSupprimer,
                        TableView<Object> stagiaireTable, TextField[] detailFields) {
        this.btnNouveauStagiaire = btnNouveauStagiaire;
        this.btnModifier = btnModifier;
        this.btnSupprimer = btnSupprimer;
        this.stagiaireTable = stagiaireTable;
        this.detailFields = detailFields;

        setupEventHandlers();
    }

    private void setupEventHandlers() {
        btnNouveauStagiaire.setOnAction(e -> handleNouveauStagiaire());
        btnModifier.setOnAction(e -> handleModifier());
        btnSupprimer.setOnAction(e -> handleSupprimer());
        
        stagiaireTable.getSelectionModel().selectedItemProperty().addListener(
            (obs, oldSelection, newSelection) -> handleSelectionChange(newSelection)
        );
    }

    private void handleNouveauStagiaire() {
        clearFields();
        enableFields(true);
        stagiaireTable.getSelectionModel().clearSelection();
    }

    private void handleModifier() {
        Stagiaire selected = (Stagiaire) stagiaireTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Sélection requise", "Veuillez sélectionner un stagiaire à modifier");
            return;
        }
        enableFields(true);
    }

    private void handleSupprimer() {
        Stagiaire selected = (Stagiaire) stagiaireTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Sélection requise", "Veuillez sélectionner un stagiaire à supprimer");
            return;
        }
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Supprimer le stagiaire");
        alert.setContentText("Êtes-vous sûr de vouloir supprimer ce stagiaire ?");
        
        if (alert.showAndWait().get() == ButtonType.OK) {
            try {
                stagiaireService.deleteStagiaire(selected.getId());
                refreshData();
                clearFields();
            } catch (Exception e) {
                showAlert("Erreur", "Impossible de supprimer le stagiaire : " + e.getMessage());
            }
        }
    }

    private void handleSelectionChange(Object stagiaire) {
        if (stagiaire != null) {
            Stagiaire s = (Stagiaire) stagiaire;
            detailFields[0].setText(s.getNom());
            detailFields[1].setText(s.getPrenom());
            detailFields[2].setText(s.getEmail());
            detailFields[3].setText(s.getTelephone());
            enableFields(false);
        }
    }

    private void clearFields() {
        for (TextField field : detailFields) {
            field.clear();
        }
    }

    private void enableFields(boolean enabled) {
        for (TextField field : detailFields) {
            field.setDisable(!enabled);
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void saveStagiaire() {
        try {
            Stagiaire stagiaire = new Stagiaire();
            stagiaire.setNom(detailFields[0].getText());
            stagiaire.setPrenom(detailFields[1].getText());
            stagiaire.setEmail(detailFields[2].getText());
            stagiaire.setTelephone(detailFields[3].getText());
            
            stagiaireService.saveStagiaire(stagiaire);
            refreshData();
            clearFields();
            enableFields(false);
        } catch (Exception e) {
            showAlert("Erreur", "Impossible de sauvegarder le stagiaire : " + e.getMessage());
        }
    }

    private void refreshData() {
        stagiaireTable.getItems().clear();
        stagiaireTable.getItems().addAll(stagiaireService.getAllStagiaires());
    }
} 