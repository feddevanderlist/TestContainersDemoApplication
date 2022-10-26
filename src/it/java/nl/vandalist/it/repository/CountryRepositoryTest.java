package nl.vandalist.it.repository;

import nl.vandalist.it.model.CountryTestDto;
import nl.vandalist.model.CountryDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepositoryTest extends CrudRepository<CountryDto, Long> {
}
