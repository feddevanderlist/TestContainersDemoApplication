package nl.vandalist.it.repository;

import nl.vandalist.it.model.AuthorTestDto;
import nl.vandalist.model.AuthorDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepositoryTest extends CrudRepository<AuthorTestDto, Long> {

    @Query("select a from AuthorDto a where lower(a.firstName) like lower(CONCAT('%',:firstname,'%'))or lower(a.lastName) like lower(CONCAT('%',:lastname,'%'))")
    List<AuthorDto> findAllByFirstNameOrLastName(@Param("firstname") String firstName, @Param("lastname") String lastName);
}
