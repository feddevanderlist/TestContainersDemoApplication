package nl.vandalist.web.resource;

import nl.vandalist.model.GebruikerDto;
import nl.vandalist.service.GebruikersService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/gebruikers")
public class GebruikerController {

    private GebruikersService gebruikersService;

    public GebruikerController(GebruikersService gebruikersService) {
        this.gebruikersService = gebruikersService;
    }

    @GetMapping(
            value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<GebruikerDto>> getGebruikers(final HttpServletRequest request) {
        final List<GebruikerDto> gebruikers = gebruikersService.haalGebruikers();
        return ResponseEntity.ok(gebruikers);
    }
}
