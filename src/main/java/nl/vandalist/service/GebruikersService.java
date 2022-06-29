package nl.vandalist.service;

import nl.vandalist.model.GebruikerDto;
import nl.vandalist.repository.GebruikerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GebruikersService {

    @Autowired
    private GebruikerRepository gebruikerRepository;

    public List<GebruikerDto> haalGebruikers() {
        return gebruikerRepository.findAll();
    }
}
