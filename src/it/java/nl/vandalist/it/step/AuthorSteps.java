package nl.vandalist.it.step;

import io.cucumber.java.nl.Als;
import io.cucumber.java.nl.Dan;
import io.cucumber.java.nl.Gegeven;
import io.restassured.response.Response;
import nl.vandalist.it.container.PostgressContainerConfig;
import nl.vandalist.it.helper.AuthorMapper;
import nl.vandalist.it.model.AuthorTestDto;
import nl.vandalist.it.support.AuthorServiceTest;
import nl.vandalist.it.support.State;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;


public class AuthorSteps {
    @Autowired
    State state;

    @Autowired
    AuthorServiceTest authorService;

    @Autowired
    AuthorMapper authorMapper;

    @Gegeven("De {string} sql is ingelezen")
    public void deAuthorSqlIsIngelezen(final String filename) {
        PostgressContainerConfig.insertIntoDatabase(filename);
    }

    @Als("de gebruiker alle authors ophaalt")
    public void deGebruikerAlleAuthorsOphaalt() {
        final Response response = authorService.getAuthors();
        state.setResponse(response);
    }

    @Dan("heeft hij {int} author")
    public void heeftHijAuthor(final int aantalAuthors) {
        final Response response = state.getResponse();
        Assertions.assertEquals(200, response.getStatusCode(), response.getBody().asPrettyString());
        Assertions.assertEquals(aantalAuthors, response.getBody().jsonPath().getList("", AuthorTestDto.class).size());
    }

    @Dan("heeft hij een author met de volgende gegevens")
    public void heeftHijEenAuthorMetDeVolgendeGegevens(final List<Map<String, String>> expectedAuthorString) {
        final Response response = state.getResponse();
        Assertions.assertEquals(200, response.getStatusCode(), response.getBody().asPrettyString());
        final AuthorTestDto author = response.getBody().as(AuthorTestDto.class);
        final AuthorTestDto expectedAuthor = authorMapper.converteerCucumberMapToAuthorTestDto(expectedAuthorString.get(0));
        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedAuthor.getFirstName(), author.getFirstName()),
                () -> Assertions.assertEquals(expectedAuthor.getLastName(), author.getLastName()),
                () -> Assertions.assertEquals(expectedAuthor.getDateOfBirth(), author.getDateOfBirth()),
                () -> Assertions.assertEquals(expectedAuthor.getCountryOfOrigin().getId(), author.getCountryOfOrigin().getId()),
                () -> Assertions.assertEquals(expectedAuthor.getCountryOfResidence().getId(), author.getCountryOfResidence().getId())
        );

    }

    @Als("de gebruiker de author met id {int} ophaalt")
    public void deGebruikerDeAuthorMetIdOphaalt(int id) {
        final Response response = authorService.getAuthor(id);
        state.setResponse(response);
    }
}
