package com.ofpo.GestionnaireFormation.ui;

import com.ofpo.GestionnaireFormation.TestConfig;
import javafx.stage.Stage;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.testfx.framework.junit5.ApplicationTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@TestPropertySource(properties = {
    "spring.main.web-application-type=none",
    "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration",
    "spring.main.allow-bean-definition-overriding=true",
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1",
    "spring.datasource.driverClassName=org.h2.Driver",
    "spring.datasource.username=sa",
    "spring.datasource.password=",
    "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect"
})
@ContextConfiguration(classes = TestConfig.class)
public abstract class BaseSpringBootTest extends ApplicationTest {

    @Autowired
    protected MainApplication mainApplication;

    @Override
    public void start(Stage stage) {
        mainApplication.start(stage);
    }
} 