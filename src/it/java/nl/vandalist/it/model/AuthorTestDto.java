package nl.vandalist.it.model;

import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorTestDto {

    private Long id;
    private String firstName;
    private String lastName;
    private CountryTestDto countryOfOrigin;
    private CountryTestDto countryOfResidence;
    private LocalDate dateOfBirth;
    private Set<LanguageTestDto> languages = new HashSet<>();
}
