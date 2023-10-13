package nl.vandalist.it.container;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SFTPContainer implements Container {

    private GenericContainer<?> container;

    public SFTPContainer() {
        try {
            container = new GenericContainer<>(DockerImageName.parse("atmoz/sftp:alpine"))
                    .withCopyFileToContainer(
                            MountableFile.forClasspathResource("testcontainers/file.txt", 0777),
                            "/home/foo/upload/testcontainers"
                    )
                    .withExposedPorts(22)
                    .withCommand("foo:pass:::upload"
                    );
        } catch (Exception e) {
            e.printStackTrace();
        }
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

}
