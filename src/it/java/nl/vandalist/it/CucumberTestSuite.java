package nl.vandalist.it;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
// Verwijst naar de feature files waarin Gherkin scenarios staan
@SelectClasspathResource("features")
// Glue, oftewel Cucumber step defs en context configuration
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "nl.vandalist.it")
// Activeer Cucumber plugins. Meestal bedoeld voor reporting: https://cucumber.io/docs/cucumber/reporting
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value =
        "pretty," + // Zorgt voor nette logging
                "html:target/reports/cucumber.html," + // Maakt een html-rapportbestand
                "junit:target/reports/junit.xml" // Maakt een junit-rapportbestand
)
@EnableJpaRepositories
public class CucumberTestSuite {
}
