package nl.vandalist.it;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import jakarta.annotation.PostConstruct;
import nl.vandalist.it.config.TestConfig;
import nl.vandalist.it.container.ContainerApplicationRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;


/**
 * De {@link CucumberSpringConfig} zorgt ervoor dat we onze Spring Boot applicatie opstarten zodra we de Cucumber tests
 * draaien. We kunnen hier zo nodig handmatige configuratie classes meegeven.
 */
@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = ContainerApplicationRunner.class, classes = TestConfig.class)
//@DirtiesContext // Deze annotatie zorgt ervoor dat de database wordt geschoond tussen ieder scenario

public class CucumberSpringConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(CucumberSpringConfig.class);

    @LocalServerPort
    private int port;

    /**
     * Als de ApplicationContext gebouwd is, willen we de poort van de applicatie weten. Dan kunnen er tegen aan praten.
     */
    @PostConstruct
    public void setUp() {
        final ObjectMapper objectMapper = new ObjectMapper()
                .findAndRegisterModules()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        RestAssured.baseURI = "http://localhost:" + port;
        RestAssured.config = RestAssured.config().objectMapperConfig(
                new ObjectMapperConfig().jackson2ObjectMapperFactory((clazz, charset) -> objectMapper));
//        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    /**
     * Deze Before-hook zorgt ervoor dat de naam van je scenario duidelijk wordt gelogd. Handig voor debuggen.
     *
     * @param scenario het huidige scenario (wordt geïnjecteerd door Cucumber)
     */

    public void printScenario(final Scenario scenario) {
        LOGGER.info("----------------------------------------------------------------");
        LOGGER.info(scenario.getName());
        LOGGER.info("----------------------------------------------------------------");
    }
}
