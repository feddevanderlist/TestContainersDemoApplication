package nl.vandalist.it.support;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import nl.vandalist.it.model.AuthorTestDto;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceTest {

    public Response getAuthors() {
        return RestAssured.given()
                .contentType("application/json")
                .get("authors")
                .andReturn();
    }

    public Response getAuthor(final int id) {
        return RestAssured.given()
                .contentType("application/json")
                .get("authors/" + id)
                .andReturn();
    }

    public Response createAuthor(final AuthorTestDto Author) {
        return RestAssured.given()
                .contentType("application/json")
                .body(Author)
                .post("authors/")
                .andReturn();
    }

    public Response updateAuthor(final long id, final AuthorTestDto Author) {
        return RestAssured.given()
                .contentType("application/json")
                .body(Author)
                .patch("authors/" + id)
                .andReturn();
    }
}
