package com.ofpo.GestionnaireFormation.ui;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import org.junit.jupiter.api.Test;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.TableViewMatchers.hasNumRows;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

public class UserInteractionTest extends BaseSpringBootTest {

    @Test
    void testTabSwitching() {
        // Vérifier que l'onglet Formations est visible par défaut
        verifyThat("#tabFormations", isVisible());

        // Cliquer sur l'onglet Utilisateurs
        clickOn("#tabUtilisateurs");
        verifyThat("#tabUtilisateurs", isVisible());

        // Cliquer sur l'onglet Rôles
        clickOn("#tabRoles");
        verifyThat("#tabRoles", isVisible());
    }

    @Test
    void testMenuQuit() {
        // Cliquer sur le menu Fichier
        clickOn("Fichier");
        
        // Vérifier que le menu Quitter est visible
        verifyThat("Quitter", isVisible());
    }

    @Test
    void testWindowResize() {
        // Simuler le redimensionnement de la fenêtre
        drag(".window").moveBy(100, 100);
        
        // Vérifier que le contenu reste visible
        verifyThat("#tabPane", isVisible());
    }
} 