package com.ofpo.GestionnaireFormation.ui.views;

import com.ofpo.GestionnaireFormation.ui.controllers.StatistiqueController;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.springframework.stereotype.Component;

@Component
public class StatistiqueView extends BorderPane {
    
    private final StatistiqueController controller;
    private Button btnActualiser;
    private Button btnSauvegarder;
    private ComboBox<String> periodeComboBox;
    private DatePicker dateDebutPicker;
    private DatePicker dateFinPicker;
    private BarChart<String, Number> formationsChart;
    private LineChart<String, Number> inscriptionsChart;
    private GridPane statsGrid;
    
    public StatistiqueView() {
        this.controller = new StatistiqueController();
        initializeView();
    }
    
    private void initializeView() {
        btnActualiser = new Button("Actualiser");
        btnSauvegarder = new Button("Sauvegarder");
        periodeComboBox = new ComboBox<>();
        dateDebutPicker = new DatePicker();
        dateFinPicker = new DatePicker();
        
        // Création des axes pour les graphiques
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        formationsChart = new BarChart<>(xAxis, yAxis);
        formationsChart.setTitle("Formations par période");
        
        CategoryAxis xAxis2 = new CategoryAxis();
        NumberAxis yAxis2 = new NumberAxis();
        inscriptionsChart = new LineChart<>(xAxis2, yAxis2);
        inscriptionsChart.setTitle("Inscriptions par période");
        
        statsGrid = new GridPane();
        VBox topBox = new VBox(10, btnActualiser, btnSauvegarder, periodeComboBox, dateDebutPicker, dateFinPicker);
        setTop(topBox);
        setCenter(formationsChart);
        setBottom(inscriptionsChart);
        setRight(statsGrid);
        controller.initialize(btnActualiser, periodeComboBox, dateDebutPicker, dateFinPicker, formationsChart, inscriptionsChart, statsGrid);
        btnSauvegarder.setOnAction(e -> controller.saveStatistiques());
    }
} 