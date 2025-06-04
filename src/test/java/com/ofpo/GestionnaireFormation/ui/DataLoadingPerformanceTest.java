package com.ofpo.GestionnaireFormation.ui;

import javafx.application.Platform;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
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
public class DataLoadingPerformanceTest extends ApplicationTest {

    private MainApplication mainApplication;
    private Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        mainApplication = new MainApplication();
        mainApplication.start(stage);
    }

    @Benchmark
    public void testFormationsLoadingPerformance() {
        Platform.runLater(() -> {
            // Test de performance pour le chargement des formations
            for (int i = 0; i < 10; i++) {
                clickOn("#tabFormations");
                // Simuler le chargement de données
                try {
                    Thread.sleep(100); // Simuler un délai de chargement
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
    }

    @Benchmark
    public void testUtilisateursLoadingPerformance() {
        Platform.runLater(() -> {
            // Test de performance pour le chargement des utilisateurs
            for (int i = 0; i < 10; i++) {
                clickOn("#tabUtilisateurs");
                // Simuler le chargement de données
                try {
                    Thread.sleep(100); // Simuler un délai de chargement
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
    }

    @Benchmark
    public void testRolesLoadingPerformance() {
        Platform.runLater(() -> {
            // Test de performance pour le chargement des rôles
            for (int i = 0; i < 10; i++) {
                clickOn("#tabRoles");
                // Simuler le chargement de données
                try {
                    Thread.sleep(100); // Simuler un délai de chargement
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(DataLoadingPerformanceTest.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }
} 