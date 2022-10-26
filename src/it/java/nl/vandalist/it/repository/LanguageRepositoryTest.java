package nl.vandalist.it.repository;

import lombok.NonNull;
import nl.vandalist.it.model.LanguageTestDto;
import nl.vandalist.model.LanguageDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageRepositoryTest extends CrudRepository<LanguageTestDto, Long> {

    List<LanguageDto> findLanguagesByNameContainingIgnoreCase(@NonNull String name);
}
