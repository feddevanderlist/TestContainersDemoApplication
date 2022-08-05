package nl.vandalist.it.model;

import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorTestDto {

    private Long id;
    private String firstName;
    private String lastName;
    private CountryTestDto countryOfOrigin;
    private CountryTestDto countryOfResidence;
    private Date dateOfBirth;
    private Set<LanguageTestDto> languages = new HashSet<>();
}
