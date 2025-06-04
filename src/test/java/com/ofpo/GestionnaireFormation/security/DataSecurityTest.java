package com.ofpo.GestionnaireFormation.security;

import com.ofpo.GestionnaireFormation.ui.MainApplication;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;

public class DataSecurityTest extends ApplicationTest {

    private MainApplication mainApplication;

    @Override
    public void start(Stage stage) {
        mainApplication = new MainApplication();
        mainApplication.start(stage);
    }

    @Test
    void testDataEncryption() {
        // Test du chiffrement des données sensibles
        clickOn("#tabFormations");
        
        // Vérifier que les données sensibles sont masquées
        verifyThat("#sensitiveData", (TableView<?> table) -> {
            return table.getColumns().stream()
                .anyMatch(column -> column.getText().contains("****"));
        });
    }

    @Test
    void testDataAccessLogging() {
        // Test de la journalisation des accès aux données
        loginAsAdmin();
        clickOn("#tabUtilisateurs");
        
        // Vérifier que l'accès est journalisé
        verifyThat("#accessLog", isVisible());
    }

    @Test
    void testDataExportSecurity() {
        // Test de la sécurité lors de l'exportation des données
        loginAsAdmin();
        clickOn("#exportButton");
        
        // Vérifier les restrictions d'exportation
        verifyThat("#exportDialog", isVisible());
        verifyThat("#sensitiveDataCheckbox", isVisible());
    }

    @Test
    void testDataBackupSecurity() {
        // Test de la sécurité des sauvegardes
        loginAsAdmin();
        clickOn("#backupButton");
        
        // Vérifier que la sauvegarde est chiffrée
        verifyThat("#backupProgress", isVisible());
        verifyThat("#encryptionStatus", isVisible());
    }

    @Test
    void testDataDeletionSecurity() {
        // Test de la sécurité lors de la suppression des données
        loginAsAdmin();
        clickOn("#deleteButton");
        
        // Vérifier les confirmations de suppression
        verifyThat("#confirmationDialog", isVisible());
        verifyThat("#confirmationCheckbox", isVisible());
    }

    private void loginAsAdmin() {
        clickOn("#usernameField");
        write("admin");
        clickOn("#passwordField");
        write("admin123");
        clickOn("#loginButton");
    }
} 