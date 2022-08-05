package nl.vandalist.web.resource;

import nl.vandalist.model.AuthorDto;
import nl.vandalist.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;


    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(
            value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<AuthorDto>> getAllAuthors() {
        List<AuthorDto> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(authors);
    }

    @GetMapping(
            value = "{authorId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable("authorId") Long authorId) {
        if (authorId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No author id in request");
        }
        final AuthorDto author = authorService.getAuthorById(authorId);
        if (author == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No author found by id");
        }
        return ResponseEntity.ok(author);
    }

    @GetMapping(
            value = "{authorName}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<AuthorDto>> getAuthorByName(@PathVariable("authorName") String authorName) {
        if (authorName == null || authorName.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No author id in request");
        }
        final List<AuthorDto> author = authorService.getAllAuthorsByName(authorName);
        if (author == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No author found by id");
        }
        return ResponseEntity.ok(author);
    }

    @PostMapping(
            value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody final AuthorDto authorDto) {
        final AuthorDto newAuthor = authorService.createAuthor(authorDto);

        return ResponseEntity.ok(newAuthor);
    }
}
