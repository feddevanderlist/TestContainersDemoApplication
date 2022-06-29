package nl.vandalist.it.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GebruikerTestDto {
    private Long id;
    private String voornaam;
    private String achternaam;
    private Integer leeftijd;
    private String titel;
    private Boolean isAdmin;
}
