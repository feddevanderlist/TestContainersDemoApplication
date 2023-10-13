package nl.vandalist.it.support;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.MultiPartSpecification;
import org.springframework.stereotype.Service;

@Service
public class FileServiceTest {
    public Response uploadFile(final MultiPartSpecification multiPartSpecification) {
        return RestAssured.given()
                .contentType("multipart/form-data")
                .multiPart(multiPartSpecification)
                .post("/file")
                .andReturn();
    }
}
