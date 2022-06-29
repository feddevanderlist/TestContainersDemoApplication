package nl.vandalist.it.config;

import nl.vandalist.TestContainersDemoApplicationTests;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;

@TestConfiguration
@Import(TestContainersDemoApplicationTests.class)
public class TestConfig {
}
