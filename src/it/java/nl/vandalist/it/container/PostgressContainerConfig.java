package nl.vandalist.it.container;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.ext.ScriptUtils;
import org.testcontainers.jdbc.JdbcDatabaseDelegate;
import org.testcontainers.utility.DockerImageName;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PostgressContainerConfig implements Container {
    private static final PostgreSQLContainer<?> postgress =
            (PostgreSQLContainer<?>) new PostgreSQLContainer(DockerImageName.parse("postgres:13-alpine"))
                    .withDatabaseName("testdb")
                    .withUsername("testuser")
                    .withPassword("testpassword")
                    .withInitScript("postgress/insert.sql")
                    .withEnv("MAX_HEAP_SIZE", "256m").withReuse(true);

    private static final String POSTGRESS_SCRIPT_LOCATION = "postgress/scripts/";

    @Override
    public void start() {
        postgress.start();
    }


    @Override
    public void stop() {
        postgress.stop();
        //do nothing, JVM handles shut down
    }

    private void fillDatabase() {
        // The class loader that loaded the class
        final ClassLoader classLoader = PostgressContainerConfig.class.getClassLoader();
        final Set<String> files = listFilesUsingFileWalk(classLoader);
        JdbcDatabaseDelegate containerDelegate = new JdbcDatabaseDelegate(postgress, "");
        files.parallelStream().forEach(fileName ->
                ScriptUtils.runInitScript(containerDelegate, POSTGRESS_SCRIPT_LOCATION + fileName));
    }

    public static void insertIntoDatabase(String filename) {
        JdbcDatabaseDelegate containerDelegate = new JdbcDatabaseDelegate(postgress, "");
        ScriptUtils.runInitScript(containerDelegate, POSTGRESS_SCRIPT_LOCATION + filename + ".sql");

    }

    private Set<String> listFilesUsingFileWalk(final ClassLoader classLoader) {
        final String scriptLocation = Objects.requireNonNull(classLoader.getResource(POSTGRESS_SCRIPT_LOCATION)).getPath();
        final String scriptLocation2 = new File(scriptLocation).getAbsolutePath();
        try (Stream<Path> stream = Files.walk(Paths.get(scriptLocation2), 1)) {
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
                "spring.datasource.url", postgress.getJdbcUrl(),
                "spring.datasource.username", postgress.getUsername(),
                "spring.datasource.password", postgress.getPassword()
        );
    }
}
