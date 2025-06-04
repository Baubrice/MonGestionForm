package com.ofpo.GestionnaireFormation.ui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

public class AccessibiliteTest extends ApplicationTest {

    private MainApplication mainApplication;

    @Override
    public void start(Stage stage) {
        mainApplication = new MainApplication();
        mainApplication.start(stage);
    }

    @Test
    void testLabelsAccessibles() {
        // Test des labels pour les lecteurs d'écran
        verifyThat("#usernameLabel", (Label label) -> 
            label.getText().length() > 0 && 
            label.getAccessibleText() != null);
            
        verifyThat("#passwordLabel", (Label label) -> 
            label.getText().length() > 0 && 
            label.getAccessibleText() != null);
    }

    @Test
    void testChampsSaisieAccessibles() {
        // Test de l'accessibilité des champs de saisie
        verifyThat("#usernameField", (TextField field) -> 
            field.getPromptText() != null && 
            field.getAccessibleText() != null);
            
        verifyThat("#passwordField", (TextField field) -> 
            field.getPromptText() != null && 
            field.getAccessibleText() != null);
    }

    @Test
    void testBoutonsAccessibles() {
        // Test de l'accessibilité des boutons
        verifyThat("#loginButton", (Button button) -> 
            button.getText().length() > 0 && 
            button.getAccessibleText() != null);
            
        verifyThat("#helpButton", (Button button) -> 
            button.getText().length() > 0 && 
            button.getAccessibleText() != null);
    }

    @Test
    void testNavigationRaccourcis() {
        // Test des raccourcis clavier
        press(javafx.scene.input.KeyCode.ALT, javafx.scene.input.KeyCode.F);
        verifyThat("#menuFichier", isVisible());
        
        press(javafx.scene.input.KeyCode.ALT, javafx.scene.input.KeyCode.H);
        verifyThat("#menuAide", isVisible());
    }

    @Test
    void testMessagesAccessibles() {
        // Test des messages d'erreur accessibles
        clickOn("#usernameField");
        write("invalid");
        press(javafx.scene.input.KeyCode.TAB);
        
        verifyThat("#errorMessage", (Label label) -> 
            label.getText().length() > 0 && 
            label.getAccessibleText() != null);
    }

    @Test
    void testContrasteCouleurs() {
        // Test du contraste des couleurs pour la lisibilité
        verifyThat("#mainContent", (javafx.scene.layout.VBox content) -> {
            String style = content.getStyle();
            return style.contains("-fx-background-color: white") &&
                   style.contains("-fx-text-fill: black");
        });
    }

    @Test
    void testTailleElements() {
        // Test de la taille des éléments interactifs
        verifyThat("#loginButton", (Button button) -> 
            button.getMinWidth() >= 44 && 
            button.getMinHeight() >= 44);
            
        verifyThat("#usernameField", (TextField field) -> 
            field.getMinHeight() >= 44);
    }

    @Test
    void testEspacementElements() {
        // Test de l'espacement entre les éléments
        verifyThat("#mainContent", (javafx.scene.layout.VBox content) -> 
            content.getSpacing() >= 8);
    }

    @Test
    void testFocusVisible() {
        // Test de la visibilité du focus
        clickOn("#usernameField");
        verifyThat("#usernameField", (TextField field) -> 
            field.isFocused() && 
            field.getStyle().contains("-fx-focus-color"));
    }

    @Test
    void testMessagesAide() {
        // Test des messages d'aide
        moveTo("#helpButton");
        verifyThat("#tooltip", (javafx.scene.control.Tooltip tooltip) -> 
            tooltip.getText().length() > 0);
    }
} 