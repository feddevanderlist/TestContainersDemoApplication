package nl.vandalist.repository;

import nl.vandalist.model.GebruikerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GebruikerRepository extends JpaRepository<GebruikerDto, Long> {
}
