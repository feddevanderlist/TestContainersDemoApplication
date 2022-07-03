package nl.vandalist.service;

import nl.vandalist.model.GebruikerDto;
import nl.vandalist.repository.GebruikerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class GebruikersService {

    @Autowired
    private GebruikerRepository gebruikerRepository;

    public List<GebruikerDto> getGebruikers() {
        return gebruikerRepository.findAll();
    }

    public GebruikerDto getGebruikerById(Long id) {
        return gebruikerRepository.findById(id).orElse(null);
    }

    public GebruikerDto createGebruiker(GebruikerDto gebruikerDto) {
        gebruikerDto.setId(null);
        return gebruikerRepository.save(gebruikerDto);
    }

    public GebruikerDto updateGebruiker(Long gebruikerId, GebruikerDto updatedGebruikerDto) {
        final GebruikerDto gebruiker = gebruikerRepository.findById(gebruikerId).orElse(null);
        if (gebruiker == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Gebruiker bestaat niet");
        }
        updatedGebruikerDto.setId(gebruiker.getId());
        return gebruikerRepository.save(updatedGebruikerDto);
    }
}
