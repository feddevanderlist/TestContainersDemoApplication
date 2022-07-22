package nl.vandalist.repository;

import nl.vandalist.model.GenreDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends CrudRepository<GenreDto, Long> {
}
