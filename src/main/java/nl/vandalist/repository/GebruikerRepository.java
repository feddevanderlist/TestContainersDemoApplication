package nl.vandalist.repository;

import nl.vandalist.model.GebruikerDto;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GebruikerRepository extends CassandraRepository<GebruikerDto, Long> {
}
