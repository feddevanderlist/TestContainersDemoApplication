package nl.vandalist.service;

import nl.vandalist.model.AuthorDto;
import nl.vandalist.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<AuthorDto> getAllAuthors() {
        return StreamSupport.stream(authorRepository.findAll().spliterator(), false).toList();
    }

    public AuthorDto getAuthorById(Long authorId) {
        return authorRepository.findById(authorId).orElse(null);
    }

    public List<AuthorDto> getAllAuthorsByName(String authorName) {
        return authorRepository.findAllByFirstNameOrLastName(authorName, authorName);

    }

    public AuthorDto createAuthor(AuthorDto authorDto) {
        return authorRepository.save(authorDto);
    }
}
