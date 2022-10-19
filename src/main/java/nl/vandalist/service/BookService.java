package nl.vandalist.service;

import nl.vandalist.model.BookDto;
import nl.vandalist.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<BookDto> getBooks() {
        return StreamSupport.stream(bookRepository.findAll().spliterator(), false).toList();
    }

    public BookDto getBookById(final Long bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }


    public BookDto createBook(final BookDto bookDto) {
        bookDto.setId(null);
        return bookRepository.save(bookDto);
    }

    public BookDto updateBook(final Long bookId, final BookDto updatedBookDto) {
        final BookDto book = bookRepository.findById(bookId).orElse(null);
        if (book == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Book with id %s does not exist", bookId));
        }
        updatedBookDto.setId(book.getId());
        return bookRepository.save(updatedBookDto);
    }

    public void deleteBook(final Long bookId) {
        final BookDto toBeDeletedDto = this.getBookById(bookId);
        if (toBeDeletedDto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("The book with %s does not exist in the database", bookId));
        }
        bookRepository.delete(toBeDeletedDto);
    }
}
