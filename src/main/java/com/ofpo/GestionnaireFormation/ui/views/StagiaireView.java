package com.ofpo.GestionnaireFormation.ui.views;

import com.ofpo.GestionnaireFormation.entities.Stagiaire;
import com.ofpo.GestionnaireFormation.ui.controllers.StagiaireUIController;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StagiaireView {
    @Autowired
    private StagiaireUIController controller;
    
    private Scene scene;
    private TableView<Stagiaire> stagiaireTable;
    private TextField matriculeField;
    private TextField nomField;
    private TextField prenomField;
    private TextField emailField;
    private TextField telephoneField;
    
    public Scene getScene() {
        if (scene == null) {
            createScene();
        }
        return scene;
    }
    
    private void createScene() {
        VBox root = new VBox(10);
        root.setStyle("-fx-padding: 10;");
        
        // Création de la table
        stagiaireTable = new TableView<>();
        TableColumn<Stagiaire, Long> idColumn = new TableColumn<>("ID");
        TableColumn<Stagiaire, String> matriculeColumn = new TableColumn<>("Matricule");
        TableColumn<Stagiaire, String> nomColumn = new TableColumn<>("Nom");
        TableColumn<Stagiaire, String> prenomColumn = new TableColumn<>("Prénom");
        TableColumn<Stagiaire, String> emailColumn = new TableColumn<>("Email");
        TableColumn<Stagiaire, String> telephoneColumn = new TableColumn<>("Téléphone");
        
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        matriculeColumn.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        
        stagiaireTable.getColumns().addAll(idColumn, matriculeColumn, nomColumn, prenomColumn, emailColumn, telephoneColumn);
        
        // Création du formulaire
        matriculeField = new TextField();
        matriculeField.setPromptText("Matricule");
        
        nomField = new TextField();
        nomField.setPromptText("Nom");
        
        prenomField = new TextField();
        prenomField.setPromptText("Prénom");
        
        emailField = new TextField();
        emailField.setPromptText("Email");
        
        telephoneField = new TextField();
        telephoneField.setPromptText("Téléphone");
        
        // Création des boutons
        Button addButton = new Button("Ajouter");
        addButton.setOnAction(e -> controller.handleAddStagiaire());
        
        Button deleteButton = new Button("Supprimer");
        deleteButton.setOnAction(e -> controller.handleDeleteStagiaire());
        
        // Ajout des composants à la vue
        root.getChildren().addAll(
            new Label("Gestion des stagiaires"),
            stagiaireTable,
            new Label("Nouveau stagiaire"),
            matriculeField,
            nomField,
            prenomField,
            emailField,
            telephoneField,
            new HBox(10, addButton, deleteButton)
        );
        
        scene = new Scene(root, 800, 600);
    }
} 