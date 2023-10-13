package nl.vandalist.it.container;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ImmuDbContainer implements Container {

    private GenericContainer<?> container;

    public ImmuDbContainer(final int port) {
        try {
            container = new GenericContainer<>("codenotary/immudb:1.3")
                    .withExposedPorts(port)
                    .waitingFor(Wait.forLogMessage(".*Web API server enabled.*", 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start() {
        System.out.printf("Starting %s: %s%n", this.getClass().getSimpleName(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        container.start();
        System.out.printf("Running %s at: %s%n", this.getClass().getSimpleName(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

    @Override
    public void stop() {
        container.stop();
    }

}
