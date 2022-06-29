package nl.vandalist.it.container;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

/**
 * Deze class zorgt ervoor dat alle gedefinieerde docker containers worden opgestart voordat de tests gaan draaien.
 * Je definieert containers als class in de containers package, en dan verwijs je daarnaar vanuit deze class.
 */
public class ContainerApplicationRunner implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    private final List<Container> containers = List.of(new CassandraContainerConfig());

    @Override
    public void initialize(@NotNull ConfigurableApplicationContext applicationContext) {
        containers.forEach(Container::start);
        containers.forEach(container -> addPropertiesToContext(container, applicationContext));
    }

    private void addPropertiesToContext(Container container, ConfigurableApplicationContext applicationContext) {
        TestPropertyValues.of(container.springConfig())
                .applyTo(applicationContext);
    }
}
