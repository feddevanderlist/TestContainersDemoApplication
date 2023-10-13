package nl.vandalist.it.step;

import io.cucumber.java.bs.A;
import io.cucumber.java.nl.Als;
import io.cucumber.java.nl.Dan;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.specification.MultiPartSpecification;
import nl.vandalist.it.support.FileServiceTest;
import nl.vandalist.it.support.State;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class FileStep {
    @Autowired
    FileServiceTest fileServiceTest;

    @Autowired
    State state;

    @Als("de gebruiker een zip upload")
    public void deGebruikerEenZipUpload() throws FileNotFoundException {
        final File file = new File("src/it/resources/apache-maven-3.9.4-bin.zip");

        if (file.exists()) {
            final MultiPartSpecification multiPartSpecification = new MultiPartSpecBuilder(file.getAbsoluteFile()).fileName(file.getName()).mimeType("application/zip").build();
            state.setResponse(fileServiceTest.uploadFile(multiPartSpecification));
        } else {
            throw new FileNotFoundException("file could not be found");
        }

    }

    @Dan("krijgt hij een status code {int} terug")
    public void krijgtHijEenStatusCodeTerug(int statuscode) {
        Assertions.assertEquals(statuscode, state.getResponse().getStatusCode());
        Assertions.assertEquals("file", state.getResponse().getBody().asString());
    }

    @Als("we een tabel toevoegen")
    public void weEenTabelToevoegen(List<Map<String, String>> testmap) {
        System.out.println(testmap);
    }
}
