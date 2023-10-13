package nl.vandalist.it.container;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class ReddisContainer implements Container {

    private static final GenericContainer<?> redis = new GenericContainer<>(DockerImageName.parse("redis:3-alpine"))
            .withExposedPorts(6379);

    @Override
    public void start() {
        System.out.printf("Starting %s: %s%n", this.getClass().getSimpleName(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        redis.start();
        System.out.printf("Running %s: %s%n", this.getClass().getSimpleName(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

    @Override
    public void stop() {
        redis.stop();
    }

    @Override
    public Map<String, String> springConfig() {
        return Map.of(
                "spring.redis.host", redis.getHost(),
                "spring.redis.port", redis.getFirstMappedPort().toString());
    }
}
