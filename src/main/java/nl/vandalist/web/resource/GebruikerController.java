package nl.vandalist.web.resource;

import nl.vandalist.model.GebruikerDto;
import nl.vandalist.service.GebruikersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<List<GebruikerDto>> getGebruikers(final HttpServletRequest request) {
        final List<GebruikerDto> gebruikers = gebruikersService.getGebruikers();

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
        final GebruikerDto nieuweGebruiker = gebruikersService.createGebruiker(gebruikerDto);
        return ResponseEntity.ok(nieuweGebruiker);
    }

    @PatchMapping(
            value = "/{gebruikerId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GebruikerDto> updateGebruiker(@PathVariable("gebruikerId") final Long gebruikerId, @RequestBody final GebruikerDto gebruikerDto) {
        if (gebruikerId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Er is geen gebruiker id mee gegeven.");
        }
        final GebruikerDto nieuweGebruiker = gebruikersService.updateGebruiker(gebruikerId, gebruikerDto);
        return ResponseEntity.ok(nieuweGebruiker);
    }
}
