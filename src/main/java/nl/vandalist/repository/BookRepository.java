package nl.vandalist.repository;

import nl.vandalist.model.BookDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<BookDto, Long> {
}
