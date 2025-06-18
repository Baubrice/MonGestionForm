package com.ofpo.GestionnaireFormation.ui.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

@Component
public class LoginView {
    
    private Scene scene;
    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;
    private Button helpButton;
    private Label errorMessage;
    
    public Scene getScene() {
        if (scene == null) {
            createScene();
        }
        return scene;
    }
    
    private void createScene() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: white;");
        
        // Labels
        Label usernameLabel = new Label("Nom d'utilisateur");
        usernameLabel.setId("usernameLabel");
        usernameLabel.setAccessibleText("Entrez votre nom d'utilisateur");
        
        Label passwordLabel = new Label("Mot de passe");
        passwordLabel.setId("passwordLabel");
        passwordLabel.setAccessibleText("Entrez votre mot de passe");
        
        // Champs de saisie
        usernameField = new TextField();
        usernameField.setId("usernameField");
        usernameField.setPromptText("Nom d'utilisateur");
        usernameField.setAccessibleText("Champ pour entrer votre nom d'utilisateur");
        usernameField.setMinHeight(44);
        
        passwordField = new PasswordField();
        passwordField.setId("passwordField");
        passwordField.setPromptText("Mot de passe");
        passwordField.setAccessibleText("Champ pour entrer votre mot de passe");
        passwordField.setMinHeight(44);
        
        // Boutons
        loginButton = new Button("Se connecter");
        loginButton.setId("loginButton");
        loginButton.setAccessibleText("Bouton pour se connecter");
        loginButton.setMinWidth(120);
        loginButton.setMinHeight(44);
        
        helpButton = new Button("Aide");
        helpButton.setId("helpButton");
        helpButton.setAccessibleText("Bouton d'aide");
        helpButton.setMinWidth(80);
        helpButton.setMinHeight(44);
        helpButton.setTooltip(new javafx.scene.control.Tooltip("Cliquez ici pour obtenir de l'aide"));
        
        // Message d'erreur
        errorMessage = new Label();
        errorMessage.setId("errorMessage");
        errorMessage.setVisible(false);
        
        // Ajout des éléments à la vue
        root.getChildren().addAll(
            usernameLabel,
            usernameField,
            passwordLabel,
            passwordField,
            loginButton,
            helpButton,
            errorMessage
        );
        
        root.setId("mainContent");
        
        scene = new Scene(root, 400, 500);
    }
    
    public TextField getUsernameField() {
        return usernameField;
    }
    
    public PasswordField getPasswordField() {
        return passwordField;
    }
    
    public Button getLoginButton() {
        return loginButton;
    }
    
    public Button getHelpButton() {
        return helpButton;
    }
    
    public Label getErrorMessage() {
        return errorMessage;
    }
} 