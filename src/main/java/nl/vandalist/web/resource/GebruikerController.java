package nl.vandalist.web.resource;

import nl.vandalist.model.AuthorDto;
import nl.vandalist.model.GebruikerDto;
import nl.vandalist.service.GebruikersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/gebruikers")
public class GebruikerController {

    private final GebruikersService gebruikersService;

    public GebruikerController(GebruikersService gebruikersService) {
        this.gebruikersService = gebruikersService;
    }

    @GetMapping(
            value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<GebruikerDto>> getGebruikers(@RequestParam(required = false, name = "gebruikersnaam") final String gebruikersnaam) {
        List<GebruikerDto> gebruikers = gebruikersService.getGebruikers();

        if (gebruikersnaam != null && !gebruikersnaam.isEmpty()) {
            List<GebruikerDto> gebruikersVoornaam = new ArrayList<>(gebruikers = gebruikers.stream().filter(gebruikerDto -> gebruikerDto.getVoornaam().toLowerCase().contains(gebruikersnaam.toLowerCase())).toList());
            List<GebruikerDto> achternaam = gebruikers.stream().filter(gebruikerDto -> gebruikerDto.getAchternaam().toLowerCase().contains(gebruikersnaam.toLowerCase())).toList();
            gebruikersVoornaam.addAll(achternaam);
            gebruikers = gebruikersVoornaam;
        }

        return ResponseEntity.ok(gebruikers);
    }

    @GetMapping(value = "/{gebruikerId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GebruikerDto> getGebruiker(@PathVariable("gebruikerId") final Long gebruikerId) {
        if (gebruikerId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Er is geen gebruiker id mee gegeven.");
        }
        final GebruikerDto gebruikerDto = gebruikersService.getGebruikerById(gebruikerId);
        if (gebruikerDto == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Geen gebruiker gevonden");
        }
        return ResponseEntity.ok(gebruikerDto);
    }

    @PostMapping(
            value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GebruikerDto> saveGebruiker(@RequestBody final GebruikerDto gebruikerDto) {
        gebruikerDto.setId(null);
        final GebruikerDto nieuweGebruiker = gebruikersService.createGebruiker(gebruikerDto);
        return ResponseEntity.ok(nieuweGebruiker);
    }

    @PatchMapping(
            value = "/{gebruikerId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GebruikerDto> updateGebruiker(@PathVariable("gebruikerId") final Long gebruikerId,
                                                        @RequestBody final GebruikerDto gebruikerDto) {
        if (gebruikerId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Er is geen gebruiker id mee gegeven.");
        }
        final GebruikerDto nieuweGebruiker = gebruikersService.updateGebruiker(gebruikerId, gebruikerDto);
        return ResponseEntity.ok(nieuweGebruiker);
    }

    @DeleteMapping(
            value = "/{gebruikerId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AuthorDto> deleteGebruiker(@PathVariable("gebruikerId") final Long gebruikerId) {
        if (gebruikerId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No gebruiker ID in request param");
        }
        gebruikersService.deleteGebruiker(gebruikerId);

        return ResponseEntity.accepted().build();
    }
}
