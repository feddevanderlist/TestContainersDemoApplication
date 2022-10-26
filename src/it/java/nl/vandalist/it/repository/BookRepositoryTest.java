package nl.vandalist.it.repository;

import nl.vandalist.it.model.BookTestDto;
import nl.vandalist.model.BookDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepositoryTest extends CrudRepository<BookDto, Long> {
}
