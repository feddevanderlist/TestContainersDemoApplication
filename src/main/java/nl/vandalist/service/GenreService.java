package nl.vandalist.service;

import nl.vandalist.model.GenreDto;
import nl.vandalist.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    public List<GenreDto> getGenres() {
        return StreamSupport.stream(genreRepository.findAll().spliterator(), false).toList();
    }

    public GenreDto getGenreById(final Long genreId) {
        return genreRepository.findById(genreId).orElse(null);
    }


    public GenreDto createGenre(final GenreDto genreDto) {
        genreDto.setId(null);
        return genreRepository.save(genreDto);
    }

    public GenreDto updateGenre(final Long genreId, final GenreDto updatedGenreDto) {
        final GenreDto genre = genreRepository.findById(genreId).orElse(null);
        if (genre == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Genre with id %s does not exist", genreId));
        }
        updatedGenreDto.setId(genre.getId());
        return genreRepository.save(updatedGenreDto);
    }

    public void deleteGenre(final Long genreId) {
        final GenreDto toBeDeletedDto = this.getGenreById(genreId);
        if (toBeDeletedDto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("The genre with %s does not exist in the database", genreId));
        }
        genreRepository.delete(toBeDeletedDto);
    }
}
