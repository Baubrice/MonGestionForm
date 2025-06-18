package com.ofpo.GestionnaireFormation;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.ofpo.GestionnaireFormation")
@EntityScan("com.ofpo.GestionnaireFormation.model")
@EnableJpaRepositories("com.ofpo.GestionnaireFormation.repository")
public class TestConfig {
} 