package nl.vandalist.it.repository;

import nl.vandalist.it.model.CountryTestDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepositoryTest extends CrudRepository<CountryTestDto, Long> {
}
