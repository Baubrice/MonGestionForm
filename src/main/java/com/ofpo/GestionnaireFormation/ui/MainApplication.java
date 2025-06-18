package com.ofpo.GestionnaireFormation.ui;

import com.ofpo.GestionnaireFormation.ui.controllers.FormationUIController;
import com.ofpo.GestionnaireFormation.ui.controllers.SessionUIController;
import com.ofpo.GestionnaireFormation.ui.controllers.StagiaireUIController;
import com.ofpo.GestionnaireFormation.ui.controllers.StatistiqueController;
import com.ofpo.GestionnaireFormation.ui.views.FormationView;
import com.ofpo.GestionnaireFormation.ui.views.LoginView;
import com.ofpo.GestionnaireFormation.ui.views.SessionView;
import com.ofpo.GestionnaireFormation.ui.views.StagiaireView;
import com.ofpo.GestionnaireFormation.ui.views.StatistiqueView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@SpringBootApplication(scanBasePackages = {"com.ofpo.GestionnaireFormation"})
public class MainApplication extends Application {

    private ConfigurableApplicationContext springContext;
    private TabPane tabPane;
    
    @Autowired
    private LoginView loginView;
    
    @Autowired
    private FormationView formationView;
    
    @Autowired
    private StagiaireView stagiaireView;
    
    @Autowired
    private SessionView sessionView;
    
    @Autowired
    private StatistiqueView statistiqueView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        if (springContext == null) {
            springContext = SpringApplication.run(MainApplication.class);
            springContext.getAutowireCapableBeanFactory().autowireBean(this);
        }
    }

    @Override
    public void start(Stage primaryStage) {
        // S'assurer que le contexte Spring est initialisé
        if (springContext == null) {
            init();
        }

        // Afficher d'abord la vue de connexion
        primaryStage.setTitle("Connexion - Gestionnaire de Formation");
        primaryStage.setScene(loginView.getScene());
        primaryStage.show();
        
        // Configurer le bouton de connexion pour afficher l'interface principale
        loginView.getLoginButton().setOnAction(e -> {
            tabPane = new TabPane();
            
            Tab formationTab = new Tab("Formations", formationView.getScene().getRoot());
            Tab stagiaireTab = new Tab("Stagiaires", stagiaireView.getScene().getRoot());
            Tab sessionTab = new Tab("Sessions", sessionView.getScene().getRoot());
            Tab statistiqueTab = new Tab("Statistiques", statistiqueView.getScene().getRoot());
            
            tabPane.getTabs().addAll(formationTab, stagiaireTab, sessionTab, statistiqueTab);
            
            Scene mainScene = new Scene(tabPane, 1200, 800);
            primaryStage.setTitle("Gestionnaire de Formation");
            primaryStage.setScene(mainScene);
        });
    }

    @Override
    public void stop() {
        if (springContext != null) {
            springContext.close();
        }
    }

    // Méthode pour les tests
    public void setSpringContext(ConfigurableApplicationContext context) {
        this.springContext = context;
        if (context != null) {
            context.getAutowireCapableBeanFactory().autowireBean(this);
        }
    }
} 