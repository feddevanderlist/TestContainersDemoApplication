package nl.vandalist.it.support;

import io.restassured.RestAssured;

import io.restassured.response.Response;
import org.springframework.stereotype.Service;

@Service
public class GebruikersServiceTest {

    public Response getGebruikers() {
        return RestAssured.given()
                .contentType("application/json")
                .get("gebruikers")
                .andReturn();
    }


}
