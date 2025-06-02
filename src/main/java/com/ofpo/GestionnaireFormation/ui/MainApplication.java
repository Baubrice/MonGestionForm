package com.ofpo.GestionnaireFormation.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MainApplication extends Application {

    private ConfigurableApplicationContext springContext;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        springContext = SpringApplication.run(MainApplication.class);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gestionnaire de Formation");

        // Création du menu
        MenuBar menuBar = new MenuBar();
        Menu menuFichier = new Menu("Fichier");
        MenuItem menuItemQuitter = new MenuItem("Quitter");
        menuFichier.getItems().add(menuItemQuitter);
        menuBar.getMenus().add(menuFichier);

        // Création des onglets
        TabPane tabPane = new TabPane();

        // Onglet Formations
        Tab tabFormations = new Tab("Formations");
        VBox formationsContent = new VBox(10);
        formationsContent.setPadding(new Insets(10));
        formationsContent.getChildren().add(new Label("Gestion des formations"));
        tabFormations.setContent(formationsContent);

        // Onglet Utilisateurs
        Tab tabUtilisateurs = new Tab("Utilisateurs");
        VBox utilisateursContent = new VBox(10);
        utilisateursContent.setPadding(new Insets(10));
        utilisateursContent.getChildren().add(new Label("Gestion des utilisateurs"));
        tabUtilisateurs.setContent(utilisateursContent);

        // Onglet Rôles
        Tab tabRoles = new Tab("Rôles");
        VBox rolesContent = new VBox(10);
        rolesContent.setPadding(new Insets(10));
        rolesContent.getChildren().add(new Label("Gestion des rôles"));
        tabRoles.setContent(rolesContent);

        tabPane.getTabs().addAll(tabFormations, tabUtilisateurs, tabRoles);

        // Layout principal
        VBox root = new VBox();
        root.getChildren().addAll(menuBar, tabPane);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Gestion de la fermeture
        menuItemQuitter.setOnAction(e -> {
            springContext.close();
            primaryStage.close();
        });
    }

    @Override
    public void stop() {
        springContext.close();
    }
} 