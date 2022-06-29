package nl.vandalist.it.container;

import org.testcontainers.containers.CassandraContainer;
import org.testcontainers.containers.delegate.CassandraDatabaseDelegate;
import org.testcontainers.ext.ScriptUtils;
import org.testcontainers.utility.DockerImageName;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class CassandraContainerConfig implements Container {
    private static final CassandraContainer<?> cassandra =
            (CassandraContainer<?>) new CassandraContainer(DockerImageName.parse("cassandra")).withInitScript("cassandra/insert.cql").withEnv("MAX_HEAP_SIZE", "256m");

    private static final String CASSANDRA_SCRIPT_LOCATION = "cassandra/scripts/";

    @Override
    public void start() {
        cassandra.start();

        // Dit is nodig voor het connecten naar de cassandra container (repositories)
        System.setProperty("spring.data.cassandra.keyspace-name", "test");
        System.setProperty("spring.data.cassandra.contact-points", cassandra.getHost());
        System.setProperty("spring.data.cassandra.port", String.valueOf(cassandra.getMappedPort(9042)));

        fillDatabase();
    }

    private void fillDatabase() {
        // The class loader that loaded the class
        final ClassLoader classLoader = CassandraContainerConfig.class.getClassLoader();
        final Set<String> files = listFilesUsingFileWalk(classLoader);

        for (String fileName : files) {
            ScriptUtils.runInitScript(
                    new CassandraDatabaseDelegate(cassandra), CASSANDRA_SCRIPT_LOCATION + fileName);
        }
    }

    private Set<String> listFilesUsingFileWalk(final ClassLoader classLoader) {
        final String scriptLocation = Objects.requireNonNull(classLoader.getResource(CASSANDRA_SCRIPT_LOCATION)).getPath();
        try (Stream<Path> stream = Files.walk(Paths.get(scriptLocation), 1)) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, String> springConfig() {
        return Map.of(
                "cassandra.port", cassandra.getMappedPort(9042).toString(),
                "cassandra.username", cassandra.getUsername(),
                "cassandra.password", cassandra.getPassword(),
                "spring.data.cassandra.local-datacenter", "datacenter1"
        );
    }
}
