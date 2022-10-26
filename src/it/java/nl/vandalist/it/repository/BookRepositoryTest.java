package nl.vandalist.it.repository;

import nl.vandalist.it.model.BookTestDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepositoryTest extends CrudRepository<BookTestDto, Long> {
}
