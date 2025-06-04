package com.ofpo.GestionnaireFormation.ui;

import com.ofpo.GestionnaireFormation.ui.MainApplication;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

public class ErgonomieTest extends ApplicationTest {

    private MainApplication mainApplication;

    @Override
    public void start(Stage stage) {
        mainApplication = new MainApplication();
        mainApplication.start(stage);
    }

    @Test
    void testAccessibiliteContraste() {
        // Vérifier le contraste des couleurs
        verifyThat("#headerLabel", (Label label) -> {
            String style = label.getStyle();
            return style.contains("-fx-text-fill: #000000") && 
                   style.contains("-fx-background-color: #FFFFFF");
        });
    }

    @Test
    void testTaillePolice() {
        // Vérifier la taille de police minimale
        verifyThat("#headerLabel", (Label label) -> {
            String style = label.getStyle();
            return style.contains("-fx-font-size: 14px");
        });
    }

    @Test
    void testNavigationClavier() {
        // Vérifier la navigation au clavier
        press(javafx.scene.input.KeyCode.TAB);
        verifyThat("#usernameField", (TextField field) -> field.isFocused());
        
        press(javafx.scene.input.KeyCode.TAB);
        verifyThat("#passwordField", (PasswordField field) -> field.isFocused());
        
        press(javafx.scene.input.KeyCode.TAB);
        verifyThat("#loginButton", (Button button) -> button.isFocused());
    }

    @Test
    void testMessagesErreur() {
        // Vérifier les messages d'erreur
        clickOn("#usernameField");
        write("invalid@email");
        press(javafx.scene.input.KeyCode.TAB);
        
        verifyThat("#errorMessage", hasText("Format d'email invalide"));
    }

    @Test
    void testTailleBoutons() {
        // Vérifier la taille minimale des boutons
        verifyThat("#loginButton", (Button button) -> 
            button.getMinWidth() >= 44 && button.getMinHeight() >= 44);
    }

    @Test
    void testMenuAccessible() {
        // Vérifier l'accessibilité du menu
        verifyThat("#menuBar", isVisible());
        verifyThat("#fileMenu", isVisible());
        verifyThat("#helpMenu", isVisible());
    }

    @Test
    void testEspacementElements() {
        // Vérifier l'espacement entre les éléments
        verifyThat("#mainContent", (VBox vbox) -> 
            vbox.getSpacing() >= 10);
    }

    @Test
    void testTooltips() {
        // Vérifier la présence des tooltips
        verifyThat("#helpButton", (Button button) -> 
            button.getTooltip() != null && 
            !button.getTooltip().getText().isEmpty());
    }

    @Test
    void testResponsiveDesign() {
        // Vérifier le design responsive
        verifyThat("#mainContent", (VBox vbox) -> 
            vbox.getPrefWidth() <= 800 && 
            vbox.getPrefHeight() <= 600);
    }

    @Test
    void testFeedbackUtilisateur() {
        // Vérifier le feedback utilisateur
        clickOn("#loginButton");
        verifyThat("#loadingIndicator", isVisible());
    }

    @Test
    void testConsistanceInterface() {
        // Vérifier la consistance de l'interface
        verifyThat("#headerLabel", (Label label) -> {
            String style = label.getStyle();
            return style.contains("-fx-font-family: 'System'") &&
                   style.contains("-fx-font-weight: bold");
        });
    }
} 