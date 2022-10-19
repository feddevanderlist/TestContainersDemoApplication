package nl.vandalist.service;

import nl.vandalist.model.AuthorDto;
import nl.vandalist.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<AuthorDto> getAuthors() {
        return StreamSupport.stream(authorRepository.findAll().spliterator(), false).toList();
    }

    public AuthorDto getAuthorById(final Long authorId) {
        return authorRepository.findById(authorId).orElse(null);
    }


    public AuthorDto createAuthor(final AuthorDto authorDto) {
        authorDto.setId(null);
        return authorRepository.save(authorDto);
    }

    public AuthorDto updateAuthor(final Long authorId, final AuthorDto updatedAuthorDto) {
        final AuthorDto author = authorRepository.findById(authorId).orElse(null);
        if (author == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Author with id %s does not exist", authorId));
        }
        updatedAuthorDto.setId(author.getId());
        return authorRepository.save(updatedAuthorDto);
    }


    public void deleteAuthor(final Long authorId) {
        final AuthorDto toBeDeletedDto = this.getAuthorById(authorId);
        if (toBeDeletedDto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("The author with %s does not exist in the database", authorId));
        }
        authorRepository.delete(toBeDeletedDto);
    }
}
