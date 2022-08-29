package nl.vandalist.web.resource;

import nl.vandalist.model.CountryDto;
import nl.vandalist.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {

    private final CountryService countryService;


    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping(
            value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<CountryDto>> getAllCountries(@RequestParam(required = false, name = "countryName") final String countryName) {
        List<CountryDto> countries = countryService.getCountries();
        if (countryName != null && !countryName.isEmpty()) {
            countries = countries.stream().filter(countryDto -> countryDto.getName().toLowerCase().contains(countryName.toLowerCase())).toList();
        }

        return ResponseEntity.ok(countries);
    }

    @GetMapping(
            value = "/{countryId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CountryDto> getCountryById(@PathVariable("countryId") final Long countryId) {
        if (countryId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No country id in request");
        }
        final CountryDto country = countryService.getCountryById(countryId);
        if (country == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No country found by id");
        }
        return ResponseEntity.ok(country);
    }

    @PostMapping(
            value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CountryDto> createCountry(@RequestBody final CountryDto countryDto) {
        countryDto.setId(null);
        final CountryDto newCountry = countryService.createCountry(countryDto);

        return ResponseEntity.ok(newCountry);
    }

    @PutMapping(
            value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CountryDto> updateCountry(@PathVariable("countryId") final Long countryId, @RequestBody final CountryDto countryDto) {
        if (countryId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No country ID in request param");
        }
        final CountryDto newCountry = countryService.updateCountry(countryId, countryDto);
        return ResponseEntity.ok(newCountry);
    }
}
