package nl.vandalist.it.support;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import nl.vandalist.it.model.GebruikerTestDto;
import org.springframework.stereotype.Service;

@Service
public class GebruikersServiceTest {

    public Response getGebruikers() {
        return RestAssured.given()
                .contentType("application/json")
                .get("gebruikers")
                .andReturn();
    }

    public Response getGebruiker(final int id) {
        return RestAssured.given()
                .contentType("application/json")
                .get("gebruikers/" + id)
                .andReturn();
    }

    public Response createGebruiker(final GebruikerTestDto gebruiker) {
        return RestAssured.given()
                .contentType("application/json")
                .body(gebruiker)
                .post("gebruikers/")
                .andReturn();
    }

    public Response updateGebruiker(final long id, final GebruikerTestDto gebruiker) {
        return RestAssured.given()
                .contentType("application/json")
                .body(gebruiker)
                .patch("gebruikers/" + id)
                .andReturn();
    }

}
