package nl.vandalist.web.resource;

import nl.vandalist.model.AuthorDto;
import nl.vandalist.service.AuthorService;
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
    public ResponseEntity<List<AuthorDto>> getAllAuthors(@RequestParam(required = false, name = "authorName") final String authorName) {
        List<AuthorDto> authors = authorService.getAuthors();
        if (authorName != null && !authorName.isEmpty()) {
            return ResponseEntity.ok(
                    authors.stream()
                            .filter(a ->
                                    a.getFirstName().toLowerCase().contains(authorName.toLowerCase())
                                            || a.getLastName().toLowerCase().contains(authorName.toLowerCase()))
                            .toList()
            );
        }

        return ResponseEntity.ok(authors);
    }

    @GetMapping(
            value = "/{authorId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable("authorId") final Long authorId) {
        if (authorId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No author id in request");
        }
        final AuthorDto author = authorService.getAuthorById(authorId);
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
        authorDto.setId(null);
        final AuthorDto newAuthor = authorService.createAuthor(authorDto);

        return ResponseEntity.ok(newAuthor);
    }

    @PatchMapping(
            value = "/{authorId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable("authorId") final Long authorId, @RequestBody final AuthorDto authorDto) {
        if (authorId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No author ID in request param");
        }
        final AuthorDto newAuthor = authorService.updateAuthor(authorId, authorDto);
        return ResponseEntity.ok(newAuthor);
    }

    @DeleteMapping(
            value = "/{authorId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AuthorDto> deleteAuthor(@PathVariable("authorId") final Long authorId) {
        if (authorId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No author ID in request param");
        }
        authorService.deleteAuthor(authorId);

        return ResponseEntity.accepted().build();
    }
}
