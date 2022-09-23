package nl.vandalist.web.resource;

import nl.vandalist.model.BookDto;
import nl.vandalist.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    
    @GetMapping(
            value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<BookDto>> getAllBooks(@RequestParam(required = false, name = "bookTitle") final String bookName) {
        List<BookDto> countries = bookService.getBooks();
        if (bookName != null && !bookName.isEmpty()) {
            countries = countries.stream().filter(bookDto -> bookDto.getTitle().toLowerCase().contains(bookName.toLowerCase())).toList();
        }

        return ResponseEntity.ok(countries);
    }

    @GetMapping(value = "/{bookId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BookDto> getBook(@PathVariable("bookId") final Long bookId) {
        final BookDto books = bookService.getBookById(bookId);

        return ResponseEntity.ok(books);
    }
    @PostMapping(
            value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BookDto> saveBook(@RequestBody final BookDto bookDto) {
        bookDto.setId(null);
        final BookDto nieuweBook = bookService.createBook(bookDto);
        return ResponseEntity.ok(nieuweBook);
    }

    @PatchMapping(
            value = "/{bookId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BookDto> updateBook(@PathVariable("bookId") final Long bookId,
                                              @RequestBody final BookDto bookDto) {
        if (bookId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No book id found in request");
        }
        final BookDto nieuweBook = bookService.updateBook(bookId, bookDto);
        return ResponseEntity.ok(nieuweBook);
    }
}
