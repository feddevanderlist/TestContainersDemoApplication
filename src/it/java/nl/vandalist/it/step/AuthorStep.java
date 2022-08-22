package nl.vandalist.it.step;

import io.cucumber.java.nl.Gegeven;
import nl.vandalist.it.container.PostgressContainerConfig;

public class AuthorStep {
    @Gegeven("De {string} sql is ingelezen")
    public void deAuthorSqlIsIngelezen(final String filename) {
        PostgressContainerConfig.insertIntoDatabase(filename);
    }
}
