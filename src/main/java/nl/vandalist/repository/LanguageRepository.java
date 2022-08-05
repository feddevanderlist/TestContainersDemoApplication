package nl.vandalist.repository;

import lombok.NonNull;
import nl.vandalist.model.LanguageDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageRepository extends CrudRepository<LanguageDto, Long> {

    List<LanguageDto> findLanguageDtosByNameContainingIgnoreCase(@NonNull String name);
}
