package nl.vandalist.it.repository;

import nl.vandalist.it.model.GenreTestDto;
import nl.vandalist.model.GenreDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepositoryTest extends CrudRepository<GenreTestDto, Long> {
}
