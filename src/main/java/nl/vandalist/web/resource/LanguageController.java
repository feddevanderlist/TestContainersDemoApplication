package nl.vandalist.web.resource;

import nl.vandalist.model.LanguageDto;
import nl.vandalist.service.LanguageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/language")
public class LanguageController {
    private final LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping(
            value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<LanguageDto>> getLanguages(@RequestParam(required = false, name = "languageTitle") final String languageName) {
        List<LanguageDto> countries = languageService.getLanguages();
        if (languageName != null && !languageName.isEmpty()) {
            countries = countries.stream().filter(languageDto -> languageDto.getName().toLowerCase().contains(languageName.toLowerCase())).toList();
        }

        return ResponseEntity.ok(countries);
    }

    @GetMapping(value = "/{languageId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<LanguageDto> getLanguageById(@PathVariable("languageId") final Long languageId) {
        final LanguageDto languages = languageService.getLanguageById(languageId);

        return ResponseEntity.ok(languages);
    }

    @PostMapping(
            value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<LanguageDto> createLanguage(@RequestBody final LanguageDto languageDto) {
        if (languageDto.getName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Geen language meegegeven aan functie");
        }
        languageDto.setId(null);
        final LanguageDto newLanguage = languageService.createLanguage(languageDto);

        return ResponseEntity.ok(newLanguage);
    }

    @PatchMapping(value = "/{languageId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<LanguageDto> updateLanguage(@PathVariable("languageId") final Long languageId,
                                                      @RequestBody final LanguageDto languageDto) {

        if (languageId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Geen ID meegegeven aan request");
        }
        final LanguageDto newLanguage = languageService.updateLanguage(languageId, languageDto);
        if (newLanguage == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Er is geen language gevonden voor het meegegeven id");
        }
        return ResponseEntity.ok(newLanguage);
    }
}
