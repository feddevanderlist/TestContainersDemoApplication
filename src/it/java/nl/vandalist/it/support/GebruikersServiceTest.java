package nl.vandalist.it.support;

import io.restassured.RestAssured;

import io.restassured.response.Response;
import nl.vandalist.model.GebruikerDto;
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

    public Response createGebruiker(final int id, final GebruikerDto gebruiker) {
        return RestAssured.given()
                .contentType("application/json")
                .body(gebruiker)
                .post("gebruikers/" + id)
                .andReturn();
    }

    public Response updateGebruiker(final int id, final GebruikerDto gebruiker) {
        return RestAssured.given()
                .contentType("application/json")
                .body(gebruiker)
                .patch("gebruikers/" + id)
                .andReturn();
    }

}
