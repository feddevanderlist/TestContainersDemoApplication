package nl.vandalist.api;

import jakarta.transaction.Transactional;
import nl.vandalist.model.CountryDto;
import nl.vandalist.service.CountryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest
class UnitTestExample {

    @Autowired
    CountryService countryService;

    @Container
    private static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(
            DockerImageName.parse("postgres:13-alpine")).withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpassword")
            .withDatabaseName("test")
            .withInitScript("postgress/insert.sql")
            .withEnv("MAX_HEAP_SIZE", "256m")
            .withReuse(false);

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", () -> "testuser");
        registry.add("spring.datasource.password", () -> "testpassword");
    }

    @Test
    void verifyContainer() {
        assertThat(postgreSQLContainer.isRunning()).isTrue();
        assertThat(postgreSQLContainer.getDockerImageName()).isEqualTo("postgres:13-alpine");
        assertThat(postgreSQLContainer.getEnvMap()).contains(entry("MAX_HEAP_SIZE", "256m"));
        assertThat(postgreSQLContainer.getUsername()).isEqualTo("testuser");
        assertThat(postgreSQLContainer.getPassword()).isEqualTo("testpassword");
        assertThat(postgreSQLContainer.getDatabaseName()).isEqualTo("test");


    }

    @Test
    @Transactional
    void testRepository() {
        assertThat(countryService.getCountries()).isEmpty();
        countryService.createCountry(CountryDto.builder().name("netherlands").capital("Amsterdam").build());
        assertThat(countryService.getCountries()).hasSize(1);
        final CountryDto countryDto = countryService.getCountryById(1L);
        assertThat(countryDto).isNotNull()
                .hasFieldOrPropertyWithValue("name", "netherlands")
                .hasFieldOrPropertyWithValue("capital", "Amsterdam");
    }
}
