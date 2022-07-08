package nl.vandalist.it.step;

import io.cucumber.java.nl.Als;
import io.cucumber.java.nl.Dan;
import io.cucumber.java.nl.En;
import io.restassured.response.Response;
import nl.vandalist.it.helper.GebruikerMapper;
import nl.vandalist.it.model.GebruikerTestDto;
import nl.vandalist.it.support.GebruikersServiceTest;
import nl.vandalist.it.support.State;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class GebruikerSteps {
    @Autowired
    State state;

    @Autowired
    GebruikersServiceTest gebruikersService;

    @Autowired
    GebruikerMapper gebruikerMapper;

    @Als("de gebruiker alle gebruikers ophaalt")
    public void deGebruikerAlleGebruikersOphaalt() {
        final Response gebruikersResponse = gebruikersService.getGebruikers();
        state.setResponse(gebruikersResponse);
    }

    @Dan("heeft hij {int} gebruikers")
    public void heeftHijGebruikers(final int aantal) {
        final Response gebruikersResponse = state.getResponse();
        Assertions.assertEquals(200, gebruikersResponse.getStatusCode());
        List<GebruikerTestDto> gebruikers = gebruikersResponse.getBody().jsonPath().getList("", GebruikerTestDto.class);
        Assertions.assertEquals(aantal, gebruikers.size());
    }

    @En("is de voornaam van deze gebruiker {string}")
    public void isDeVoornaamVanDezeGebruiker(final String voornaam) {
        Assertions.assertEquals(voornaam, state.getResponse().getBody().jsonPath().getList("", GebruikerTestDto.class).get(0).getVoornaam());
    }

    @Als("de gebruiker de gebruiker met id {int} ophaalt")
    public void deGebruikerDeGebruikerMetIdOphaalt(final int gebruikerId) {
        final Response getGebruikerRespons = gebruikersService.getGebruiker(gebruikerId);
        state.setResponse(getGebruikerRespons);
    }

    @Dan("heeft hij een gebruiker met de volgende gegevens")
    public void heeftHijEenGebruikerMetDeVolgendeGegevens(final List<Map<String, String>> gebruikerGegevens) {
        final Response gebruikerResponse = state.getResponse();
        Assertions.assertEquals(200, gebruikerResponse.getStatusCode());
        final GebruikerTestDto gebruiker = gebruikerResponse.getBody().as(GebruikerTestDto.class);
        final GebruikerTestDto expectedGebruiker = gebruikerMapper.converteerCucumberMapToGebruikerTestDto(gebruikerGegevens.get(0));
        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedGebruiker.getId(), gebruiker.getId()),
                () -> Assertions.assertEquals(expectedGebruiker.getVoornaam(), gebruiker.getVoornaam()),
                () -> Assertions.assertEquals(expectedGebruiker.getAchternaam(), gebruiker.getAchternaam()),
                () -> Assertions.assertEquals(expectedGebruiker.getLeeftijd(), gebruiker.getLeeftijd()),
                () -> Assertions.assertEquals(expectedGebruiker.getTitel(), gebruiker.getTitel()),
                () -> Assertions.assertEquals(expectedGebruiker.isAdmin(), gebruiker.isAdmin())
        );
    }
}
