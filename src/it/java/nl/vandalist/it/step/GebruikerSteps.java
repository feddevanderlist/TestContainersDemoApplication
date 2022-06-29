package nl.vandalist.it.step;

import io.cucumber.java.nl.Als;
import io.cucumber.java.nl.Dan;
import io.cucumber.java.nl.En;
import io.restassured.response.Response;
import nl.vandalist.it.model.GebruikerTestDto;
import nl.vandalist.it.support.GebruikersServiceTest;
import nl.vandalist.it.support.State;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GebruikerSteps {
    @Autowired
    State state;

    @Autowired
    GebruikersServiceTest gebruikersService;

    @Als("de gebruiker alle gebruikers ophaalt")
    public void deGebruikerAlleGebruikersOphaalt() {
        final Response gebruikersResponse = gebruikersService.getGebruikers();
        state.setResponse(gebruikersResponse);
    }

    @Dan("heeft hij {int} gebruikers")
    public void heeftHijGebruikers(int aantal) {
        final Response gebruikersResponse = state.getResponse();
        Assertions.assertEquals(200, gebruikersResponse.getStatusCode());
        List<GebruikerTestDto> gebruikers = gebruikersResponse.getBody().jsonPath().getList("", GebruikerTestDto.class);
        Assertions.assertEquals(aantal, gebruikers.size());

    }

    @En("is de voornaam van deze gebruiker {string}")
    public void isDeVoornaamVanDezeGebruiker() {

    }
}
