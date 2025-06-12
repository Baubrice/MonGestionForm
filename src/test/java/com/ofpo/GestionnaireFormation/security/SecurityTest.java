package com.ofpo.GestionnaireFormation.security;

import com.ofpo.GestionnaireFormation.ui.BaseSpringBootTest;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

public class SecurityTest extends BaseSpringBootTest {

    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void start(Stage stage) {
        super.start(stage);
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Test
    void testLoginValidation() {
        // Test de validation du formulaire de connexion
        clickOn("#usernameField");
        write("testuser");
        clickOn("#passwordField");
        write("password123");

        // Vérifier que les champs sont correctement remplis
        verifyThat("#usernameField", (TextField field) -> 
            field.getText().equals("testuser"));
        verifyThat("#passwordField", (PasswordField field) -> 
            field.getText().equals("password123"));

        // Vérifier que le bouton de connexion est activé
        verifyThat("#loginButton", (javafx.scene.control.Button button) -> !button.isDisabled());
    }

    @Test
    void testPasswordEncryption() {
        String rawPassword = "password123";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // Vérifier que le mot de passe est correctement encodé
        assert passwordEncoder.matches(rawPassword, encodedPassword);
        assert !passwordEncoder.matches("wrongpassword", encodedPassword);
    }

    @Test
    void testAccessControl() {
        // Test du contrôle d'accès
        loginAsUser();
        
        // Vérifier que l'utilisateur a accès à ses fonctionnalités
        verifyThat("#userPanel", isVisible());
    }

    @Test
    void testInputValidation() {
        // Test de la validation des entrées
        clickOn("#usernameField");
        write("invalid@email");
        press(javafx.scene.input.KeyCode.TAB);
        
        // Vérifier le message d'erreur
        verifyThat("#errorMessage", hasText("Format d'email invalide"));
    }

    @Test
    void testSessionManagement() {
        // Test de la gestion des sessions
        loginAsUser();
        
        // Vérifier que les informations de session sont correctes
        verifyThat("#userInfo", hasText("user"));
    }

    private void loginAsUser() {
        clickOn("#usernameField");
        write("user");
        clickOn("#passwordField");
        write("user123");
        clickOn("#loginButton");
    }
} 