package nl.vandalist.it.helper;

import nl.vandalist.it.model.AuthorTestDto;
import nl.vandalist.it.model.CountryTestDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;

@Service
public class AuthorMapper {

    public AuthorTestDto converteerCucumberMapToAuthorTestDto(final Map<String, String> authorGegevens) {
        final AuthorTestDto authorTestDto = new AuthorTestDto();
        if (!Objects.equals(authorGegevens.get("id"), null)) {
            authorTestDto.setId(Long.valueOf(authorGegevens.get("id")));
        }
        authorTestDto.setFirstName(authorGegevens.get("first_name"));
        authorTestDto.setLastName(authorGegevens.get("last_name"));
        authorTestDto.setDateOfBirth(LocalDateTime.parse(authorGegevens.get("date_of_birth"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")).toLocalDate());
        authorTestDto.setCountryOfOrigin(getCountryOfOriginFromGegevens(authorGegevens));
        authorTestDto.setCountryOfResidence(getCountryOfResidenceFromGegevens(authorGegevens));

        return authorTestDto;
    }

    private CountryTestDto getCountryOfResidenceFromGegevens(final Map<String, String> authorGegevens) {
        final CountryTestDto countryOfResidence = new CountryTestDto();
        countryOfResidence.setId(Long.valueOf(authorGegevens.get("residence_id")));
        countryOfResidence.setName(authorGegevens.get("residence_country_name"));
        countryOfResidence.setCapital(authorGegevens.get("residence_capital"));

        return countryOfResidence;
    }

    private CountryTestDto getCountryOfOriginFromGegevens(final Map<String, String> authorGegevens) {
        final CountryTestDto countryOfOrigin = new CountryTestDto();
        countryOfOrigin.setId(Long.valueOf(authorGegevens.get("origin_id")));
        countryOfOrigin.setName(authorGegevens.get("origin_country_name"));
        countryOfOrigin.setCapital(authorGegevens.get("origin_capital"));

        return countryOfOrigin;
    }


}
