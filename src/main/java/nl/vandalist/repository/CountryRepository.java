package nl.vandalist.repository;

import nl.vandalist.model.CountryDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CrudRepository<CountryDto, Long> {
}
