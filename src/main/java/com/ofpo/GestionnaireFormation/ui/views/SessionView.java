package com.ofpo.GestionnaireFormation.ui.views;

import com.ofpo.GestionnaireFormation.entities.Session;
import com.ofpo.GestionnaireFormation.entities.Stagiaire;
import com.ofpo.GestionnaireFormation.ui.controllers.SessionUIController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;

@Component
public class SessionView {
    @Autowired
    private SessionUIController controller;
    
    private Scene scene;
    private TableView<Session> sessionTable;
    private TextField formationField;
    private DatePicker dateDebutPicker;
    private DatePicker dateFinPicker;
    private TextField formateurField;
    private TextField nombrePlacesField;
    
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
        sessionTable = new TableView<>();
        TableColumn<Session, Long> idColumn = new TableColumn<>("ID");
        TableColumn<Session, String> formationColumn = new TableColumn<>("Formation");
        TableColumn<Session, LocalDate> dateDebutColumn = new TableColumn<>("Date de début");
        TableColumn<Session, LocalDate> dateFinColumn = new TableColumn<>("Date de fin");
        TableColumn<Session, String> formateurColumn = new TableColumn<>("Formateur");
        TableColumn<Session, Integer> nombrePlacesColumn = new TableColumn<>("Nombre de places");
        
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        formationColumn.setCellValueFactory(new PropertyValueFactory<>("formation"));
        dateDebutColumn.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        dateFinColumn.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        formateurColumn.setCellValueFactory(new PropertyValueFactory<>("formateur"));
        nombrePlacesColumn.setCellValueFactory(new PropertyValueFactory<>("nombrePlaces"));
        
        sessionTable.getColumns().addAll(idColumn, formationColumn, dateDebutColumn, dateFinColumn, formateurColumn, nombrePlacesColumn);
        
        // Création du formulaire
        formationField = new TextField();
        formationField.setPromptText("Formation");
        
        dateDebutPicker = new DatePicker();
        dateDebutPicker.setPromptText("Date de début");
        
        dateFinPicker = new DatePicker();
        dateFinPicker.setPromptText("Date de fin");
        
        formateurField = new TextField();
        formateurField.setPromptText("Formateur");
        
        nombrePlacesField = new TextField();
        nombrePlacesField.setPromptText("Nombre de places");
        
        // Création des boutons
        Button addButton = new Button("Ajouter");
        addButton.setOnAction(e -> controller.handleAddSession());
        
        Button deleteButton = new Button("Supprimer");
        deleteButton.setOnAction(e -> controller.handleDeleteSession());
        
        // Ajout des composants à la vue
        root.getChildren().addAll(
            new Label("Gestion des sessions"),
            sessionTable,
            new Label("Nouvelle session"),
            formationField,
            dateDebutPicker,
            dateFinPicker,
            formateurField,
            nombrePlacesField,
            new HBox(10, addButton, deleteButton)
        );
        
        scene = new Scene(root, 800, 600);
    }
} 