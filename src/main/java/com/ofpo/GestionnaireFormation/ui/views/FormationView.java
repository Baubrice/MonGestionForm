package com.ofpo.GestionnaireFormation.ui.views;

import com.ofpo.GestionnaireFormation.entities.Formation;
import com.ofpo.GestionnaireFormation.ui.controllers.FormationUIController;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormationView extends BorderPane {
    
    private Button btnNouvelleFormation;
    private Button btnModifier;
    private Button btnSupprimer;
    private Button btnSauvegarder;
    @FXML
    private TableView<Formation> formationTable;
    private TextField[] detailFields;
    
    private final FormationUIController formationController;
    
    @Autowired
    public FormationView(FormationUIController formationController) {
        this.formationController = formationController;
        initializeView();
    }
    
    private void initializeView() {
        // Création des boutons
        btnNouvelleFormation = new Button("Nouvelle Formation");
        btnModifier = new Button("Modifier");
        btnSupprimer = new Button("Supprimer");
        btnSauvegarder = new Button("Sauvegarder");
        
        // Création de la barre d'outils
        ToolBar toolBar = new ToolBar(
            btnNouvelleFormation,
            btnModifier,
            btnSupprimer,
            btnSauvegarder
        );
        
        // Création du tableau des formations
        formationTable = new TableView<>();
        formationTable.getColumns().addAll(
            new TableColumn<>("Code"),
            new TableColumn<>("Titre"),
            new TableColumn<>("Durée"),
            new TableColumn<>("Prix")
        );
        
        // Création du panneau de détail
        VBox detailPane = new VBox(10);
        detailFields = new TextField[4];
        detailFields[0] = new TextField();
        detailFields[1] = new TextField();
        detailFields[2] = new TextField();
        detailFields[3] = new TextField();
        
        detailPane.getChildren().addAll(
            new Label("Détails de la formation"),
            new Label("Code:"),
            detailFields[0],
            new Label("Titre:"),
            detailFields[1],
            new Label("Durée:"),
            detailFields[2],
            new Label("Prix:"),
            detailFields[3]
        );
        
        // Configuration du layout
        setTop(toolBar);
        setCenter(formationTable);
        setRight(detailPane);
        
        // Initialisation du contrôleur
        formationController.initialize();
        
        // Configuration des événements
        btnNouvelleFormation.setOnAction(e -> formationController.handleAddFormation());
        btnModifier.setOnAction(e -> formationController.handleModifyFormation());
        btnSupprimer.setOnAction(e -> formationController.handleDeleteFormation());
        btnSauvegarder.setOnAction(e -> formationController.handleAddFormation());
    }
} 