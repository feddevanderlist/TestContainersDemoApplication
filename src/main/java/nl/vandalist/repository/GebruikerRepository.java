package nl.vandalist.repository;

import nl.vandalist.model.GebruikerDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GebruikerRepository extends CrudRepository<GebruikerDto, Long> {
}
