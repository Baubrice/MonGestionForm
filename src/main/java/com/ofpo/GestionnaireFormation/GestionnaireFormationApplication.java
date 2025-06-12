package com.ofpo.GestionnaireFormation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Classe principale de l'application Gestionnaire de Formation.
 * Cette classe initialise l'application Spring Boot et configure l'interface JavaFX.
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class GestionnaireFormationApplication {

	/**
	 * Point d'entrée principal de l'application.
	 * Lance l'application Spring Boot.
	 */
	public static void main(String[] args) {
		SpringApplication.run(GestionnaireFormationApplication.class, args);
	}
}
