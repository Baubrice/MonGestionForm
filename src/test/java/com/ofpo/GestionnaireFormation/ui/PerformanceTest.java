package com.ofpo.GestionnaireFormation.ui;

import javafx.application.Platform;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.testfx.framework.junit5.ApplicationTest;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 1)
@Warmup(iterations = 2)
@Measurement(iterations = 5)
public class PerformanceTest extends ApplicationTest {

    private MainApplication mainApplication;
    private Stage stage;
    private TabPane tabPane;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        mainApplication = new MainApplication();
        mainApplication.start(stage);
        tabPane = lookup("#tabPane").query();
    }

    @Benchmark
    public void testTabSwitchingPerformance() {
        Platform.runLater(() -> {
            // Test de performance pour le changement d'onglets
            for (int i = 0; i < 100; i++) {
                tabPane.getSelectionModel().selectNext();
            }
        });
    }

    @Benchmark
    public void testWindowResizePerformance() {
        Platform.runLater(() -> {
            // Test de performance pour le redimensionnement
            for (int i = 0; i < 50; i++) {
                stage.setWidth(stage.getWidth() + 10);
                stage.setHeight(stage.getHeight() + 10);
            }
        });
    }

    @Benchmark
    public void testMenuInteractionPerformance() {
        Platform.runLater(() -> {
            // Test de performance pour les interactions avec le menu
            for (int i = 0; i < 50; i++) {
                clickOn("Fichier");
                clickOn("Quitter");
            }
        });
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(PerformanceTest.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }
} 