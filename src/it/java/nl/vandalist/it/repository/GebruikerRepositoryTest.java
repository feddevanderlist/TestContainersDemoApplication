package nl.vandalist.it.repository;

import nl.vandalist.it.model.GebruikerTestDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GebruikerRepositoryTest extends CrudRepository<GebruikerTestDto, Long> {
}
