package com.ofpo.GestionnaireFormation.ui.controllers;

import com.ofpo.GestionnaireFormation.entities.Formation;
import com.ofpo.GestionnaireFormation.entities.Session;
import com.ofpo.GestionnaireFormation.entities.Stagiaire;
import com.ofpo.GestionnaireFormation.service.FormationService;
import com.ofpo.GestionnaireFormation.service.SessionService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Contrôleur pour la gestion des sessions de formation dans l'interface utilisateur.
 * Ce contrôleur gère toutes les interactions avec la vue des sessions.
 */
@Controller
public class SessionUIController {

    // Services pour la gestion des sessions et des formations
    @Autowired
    private SessionService sessionService;

    @Autowired
    private FormationService formationService;

    // Composants FXML de l'interface utilisateur
    @FXML
    private TableView<Session> sessionTable;
    @FXML
    private TableColumn<Session, Long> idColumn;
    @FXML
    private TableColumn<Session, String> formationColumn;
    @FXML
    private TableColumn<Session, LocalDate> dateDebutColumn;
    @FXML
    private TableColumn<Session, LocalDate> dateFinColumn;
    @FXML
    private TableColumn<Session, String> formateurColumn;
    @FXML
    private TableColumn<Session, Integer> nombrePlacesColumn;
    @FXML
    private TextField formationField;
    @FXML
    private DatePicker dateDebutPicker;
    @FXML
    private DatePicker dateFinPicker;
    @FXML
    private TextField formateurField;
    @FXML
    private TextField nombrePlacesField;

    /**
     * Initialise le contrôleur après le chargement du FXML.
     * Configure les colonnes de la table et charge les données initiales.
     */
    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        formationColumn.setCellValueFactory(new PropertyValueFactory<>("formation"));
        dateDebutColumn.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        dateFinColumn.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        formateurColumn.setCellValueFactory(new PropertyValueFactory<>("formateur"));
        nombrePlacesColumn.setCellValueFactory(new PropertyValueFactory<>("nombrePlaces"));
        
        refreshSessionTable();
    }

    /**
     * Charge la liste des sessions depuis le service.
     */
    private void refreshSessionTable() {
        ObservableList<Session> sessions = FXCollections.observableArrayList(sessionService.getAllSessions());
        sessionTable.setItems(sessions);
    }

    /**
     * Gère l'ajout d'une nouvelle session.
     */
    @FXML
    public void handleAddSession() {
        try {
            Formation formation = formationService.findByCode(formationField.getText());
            if (formation == null) {
                showError("Erreur", "Formation non trouvée");
                return;
            }

            Session session = new Session();
            session.setFormation(formation);
            session.setDateDebut(dateDebutPicker.getValue());
            session.setDateFin(dateFinPicker.getValue());
            session.setNombrePlaces(Integer.parseInt(nombrePlacesField.getText()));
            
            sessionService.saveSession(session);
            refreshSessionTable();
            clearFields();
        } catch (Exception e) {
            showError("Erreur lors de l'ajout de la session", e.getMessage());
        }
    }

    /**
     * Gère la suppression d'une session sélectionnée.
     */
    @FXML
    public void handleDeleteSession() {
        Session selectedSession = sessionTable.getSelectionModel().getSelectedItem();
        if (selectedSession != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText("Supprimer la session");
            alert.setContentText("Êtes-vous sûr de vouloir supprimer cette session ?");
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                sessionService.deleteSession(selectedSession.getId());
                refreshSessionTable();
            }
        } else {
            showError("Erreur", "Veuillez sélectionner une session à supprimer");
        }
    }

    /**
     * Réinitialise tous les champs du formulaire.
     */
    private void clearFields() {
        formationField.clear();
        dateDebutPicker.setValue(null);
        dateFinPicker.setValue(null);
        formateurField.clear();
        nombrePlacesField.clear();
    }

    private void showError(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
} 