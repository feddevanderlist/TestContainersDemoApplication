package nl.vandalist.it.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GebruikerTestDto {
    private Long id;
    private String voornaam;
    private String achternaam;
    private Integer leeftijd;
    private String titel;
    private boolean isAdmin;

}
