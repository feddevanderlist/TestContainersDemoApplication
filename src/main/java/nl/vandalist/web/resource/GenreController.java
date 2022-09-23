package nl.vandalist.web.resource;

import nl.vandalist.model.GenreDto;
import nl.vandalist.service.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;


    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping(
            value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<GenreDto>> getGenres(@RequestParam(required = false, name = "genreName") final String genreName) {
        List<GenreDto> countries = genreService.getGenres();
        if (genreName != null && !genreName.isEmpty()) {
            countries = countries.stream().filter(genreDto -> genreDto.getName().toLowerCase().contains(genreName.toLowerCase())).toList();
        }

        return ResponseEntity.ok(countries);
    }

    @GetMapping(
            value = "/{genreId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GenreDto> getGenreById(@PathVariable("genreId") final Long genreId) {
        if (genreId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No genre id in request");
        }
        final GenreDto genre = genreService.getGenreById(genreId);
        if (genre == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No genre found by id");
        }
        return ResponseEntity.ok(genre);
    }

    @PostMapping(
            value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GenreDto> createGenre(@RequestBody final GenreDto genreDto) {
        genreDto.setId(null);
        final GenreDto newGenre = genreService.createGenre(genreDto);

        return ResponseEntity.ok(newGenre);
    }

    @PutMapping(
            value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GenreDto> updateGenre(@PathVariable("genreId") final Long genreId, @RequestBody final GenreDto genreDto) {
        if (genreId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No genre ID in request param");
        }
        final GenreDto newGenre = genreService.updateGenre(genreId, genreDto);
        return ResponseEntity.ok(newGenre);
    }
}
