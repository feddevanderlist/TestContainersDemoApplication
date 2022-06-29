package nl.vandalist.repository;

import nl.vandalist.model.GebruikerDto;
import org.springframework.data.cassandra.repository.CassandraRepository;


public interface GebruikerRepository extends CassandraRepository<GebruikerDto, Long> {
}
