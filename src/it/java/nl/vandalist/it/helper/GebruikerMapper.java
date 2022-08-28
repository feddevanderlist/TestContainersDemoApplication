package nl.vandalist.it.helper;

import nl.vandalist.it.model.GebruikerTestDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GebruikerMapper {

    public GebruikerTestDto converteerCucumberMapToGebruikerTestDto(final Map<String, String> gebruikerGegevens) {
        final GebruikerTestDto gebruikerTestDto = new GebruikerTestDto();

        gebruikerTestDto.setId(Long.valueOf(gebruikerGegevens.get("id")));
        gebruikerTestDto.setVoornaam(gebruikerGegevens.get("voornaam"));
        gebruikerTestDto.setAchternaam(gebruikerGegevens.get("achternaam"));
        gebruikerTestDto.setLeeftijd(Integer.valueOf(gebruikerGegevens.get("leeftijd")));
        gebruikerTestDto.setTitel(gebruikerGegevens.get("titel"));
        gebruikerTestDto.setAdmin(Boolean.parseBoolean(gebruikerGegevens.get("is_admin")));

        return gebruikerTestDto;
    }
}
