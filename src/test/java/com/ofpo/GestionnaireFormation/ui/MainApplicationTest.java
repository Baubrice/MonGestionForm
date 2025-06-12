package com.ofpo.GestionnaireFormation.ui;

import javafx.scene.control.Tab;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import static org.testfx.matcher.base.NodeMatchers.isNotNull;
import org.testfx.api.FxToolkit;

public class MainApplicationTest extends BaseSpringBootTest {

    @Test
    void testInitialWindowTitle() throws Exception {
        Stage stage = FxToolkit.registerPrimaryStage();
        verifyThat(stage.getTitle(), s -> s.equals("Gestionnaire de Formation"));
    }

    @Test
    void testFormationsTabContent() {
        verifyThat("#tabFormations", isNotNull());
        verifyThat("#tabFormations", hasText("Formations"));
    }

    @Test
    void testUtilisateursTabContent() {
        verifyThat("#tabUtilisateurs", isNotNull());
        verifyThat("#tabUtilisateurs", hasText("Utilisateurs"));
    }

    @Test
    void testRolesTabContent() {
        verifyThat("#tabRoles", isNotNull());
        verifyThat("#tabRoles", hasText("Rôles"));
    }

    @Test
    void testMenuBar() {
        verifyThat("#menuBar", isVisible());
    }

    @Test
    void testStatusBar() {
        verifyThat("#statusBar", isVisible());
    }
} 