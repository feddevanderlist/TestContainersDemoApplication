package nl.vandalist.repository;

import nl.vandalist.model.LanguageDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends CrudRepository<LanguageDto, Long> {
}
