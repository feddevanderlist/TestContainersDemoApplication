package nl.vandalist.it.container;

import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.utility.DockerImageName;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class EmbeddedKafkaContainer implements Container {

    private static KafkaContainer container;

    public EmbeddedKafkaContainer() {

        container = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka"))
                .withEmbeddedZookeeper()
//                .withEnv(Map.of(
//                        "KAFKA_LISTENERS", "INSIDE://0.0.0.0:9092, OUTSIDE://0.0.0.0:19092",
//                        "KAFKA_ADVERTISED_LISTENERS", "INSIDE://kafka:9092, OUTSIDE://localhost:19092",
//                        "KAFKA_LISTENER_SECURITY_PROTOCOL_MAP", "INSIDE:PLAINTEXT, OUTSIDE:PLAINTEXT",
//                        "KAFKA_INTER_BROKER_LISTENER_NAME", "INSIDE"
//                ))
//                .withReuse(true)
        ;
    }

    @Override
    public void start() {
        System.out.printf("Starting %s: %s%n", this.getClass().getSimpleName(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        container.start();
        System.out.printf("Running %s: %s%n", this.getClass().getSimpleName(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

    @Override
    public void stop() {
        container.stop();
    }

    @Override
    public Map<String, String> springConfig() {
        return Map.of(
                "spring.kafka.bootstrap-servers", container.getBootstrapServers()
        );
    }
}