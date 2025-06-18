package com.ofpo.GestionnaireFormation.ui.controllers;

import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.springframework.stereotype.Controller;
import java.time.LocalDate;

@Controller
public class StatistiqueController {
    private Button btnActualiser;
    private ComboBox<String> periodeComboBox;
    private DatePicker dateDebutPicker;
    private DatePicker dateFinPicker;
    private BarChart<String, Number> formationsChart;
    private LineChart<String, Number> inscriptionsChart;
    private GridPane statsGrid;

    public void initialize(Button btnActualiser, ComboBox<String> periodeComboBox,
                        DatePicker dateDebutPicker, DatePicker dateFinPicker,
                        BarChart<String, Number> formationsChart,
                        LineChart<String, Number> inscriptionsChart,
                        GridPane statsGrid) {
        this.btnActualiser = btnActualiser;
        this.periodeComboBox = periodeComboBox;
        this.dateDebutPicker = dateDebutPicker;
        this.dateFinPicker = dateFinPicker;
        this.formationsChart = formationsChart;
        this.inscriptionsChart = inscriptionsChart;
        this.statsGrid = statsGrid;

        setupEventHandlers();
        initializePeriodeComboBox();
    }

    private void setupEventHandlers() {
        btnActualiser.setOnAction(e -> handleActualiser());
        periodeComboBox.setOnAction(e -> handlePeriodeChange());
        dateDebutPicker.valueProperty().addListener((obs, oldVal, newVal) -> validateDates());
        dateFinPicker.valueProperty().addListener((obs, oldVal, newVal) -> validateDates());
    }

    private void initializePeriodeComboBox() {
        periodeComboBox.getItems().addAll(
            "Aujourd'hui",
            "Cette semaine",
            "Ce mois",
            "Cette année",
            "Personnalisé"
        );
        periodeComboBox.setValue("Ce mois");
    }

    private void handleActualiser() {
        // TODO: Implémenter l'actualisation des statistiques
        updateCharts();
        updateStatsGrid();
    }

    private void handlePeriodeChange() {
        String selectedPeriode = periodeComboBox.getValue();
        LocalDate today = LocalDate.now();
        
        switch (selectedPeriode) {
            case "Aujourd'hui":
                dateDebutPicker.setValue(today);
                dateFinPicker.setValue(today);
                break;
            case "Cette semaine":
                dateDebutPicker.setValue(today.minusDays(today.getDayOfWeek().getValue() - 1));
                dateFinPicker.setValue(today);
                break;
            case "Ce mois":
                dateDebutPicker.setValue(today.withDayOfMonth(1));
                dateFinPicker.setValue(today);
                break;
            case "Cette année":
                dateDebutPicker.setValue(today.withDayOfYear(1));
                dateFinPicker.setValue(today);
                break;
            case "Personnalisé":
                // Ne rien faire, l'utilisateur choisira les dates
                break;
        }
        
        handleActualiser();
    }

    private void validateDates() {
        LocalDate dateDebut = dateDebutPicker.getValue();
        LocalDate dateFin = dateFinPicker.getValue();
        
        if (dateDebut != null && dateFin != null && dateDebut.isAfter(dateFin)) {
            showAlert("Dates invalides", "La date de début doit être antérieure à la date de fin.");
            dateFinPicker.setValue(dateDebut);
        }
    }

    private void updateCharts() {
        // TODO: Implémenter la mise à jour des graphiques avec les données réelles
        formationsChart.getData().clear();
        inscriptionsChart.getData().clear();
        
        // Exemple de données fictives
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Formation 1", 10));
        series.getData().add(new XYChart.Data<>("Formation 2", 15));
        series.getData().add(new XYChart.Data<>("Formation 3", 8));
        formationsChart.getData().add(series);
    }

    private void updateStatsGrid() {
        // TODO: Implémenter la mise à jour des statistiques avec les données réelles
        // Exemple de mise à jour des labels
        for (int i = 0; i < statsGrid.getChildren().size(); i++) {
            if (statsGrid.getChildren().get(i) instanceof Label) {
                Label label = (Label) statsGrid.getChildren().get(i);
                if (label.getText().equals("0")) {
                    label.setText("42"); // Valeur fictive
                }
            }
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void saveStatistiques() {
        // À implémenter selon les besoins (sauvegarde des statistiques)
    }
} 