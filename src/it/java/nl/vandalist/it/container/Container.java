package nl.vandalist.it.container;

import org.testcontainers.lifecycle.Startable;

import java.util.Collections;
import java.util.Map;

/**
 * Simpele interface, zodat je uniform - op een Spring manier - TestContainers kunt starten.
 * Zie {@link ContainerApplicationRunner} en {@link PostgresContainer}.
 */
public interface Container extends Startable {

    /**
     * Dit is een blocking methode die een docker container start, en wacht tot die volledig opgestart is.
     * Zorg dus altijd dat je container een wait/startup strategy bevat, zodat je geen handmatige sleeps hoeft in te
     * bouwen.
     * Zie <a href="https://www.testcontainers.org/features/startup_and_waits/">wait/startup strategies</a>.
     */
    void start();

    void stop();

    /**
     * Deze properties worden toegevoegd aan de Spring ApplicationContext.
     *
     * @return een Map waarvan de keys Spring config properties zijn, en de values de bijbehorende waarden
     */
    default Map<String, String> springConfig() {
        return Collections.emptyMap();
    }
}
