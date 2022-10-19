package nl.vandalist.service;

import nl.vandalist.model.CountryDto;
import nl.vandalist.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public List<CountryDto> getCountries() {
        return StreamSupport.stream(countryRepository.findAll().spliterator(), false).toList();
    }

    public CountryDto getCountryById(final Long countryId) {
        return countryRepository.findById(countryId).orElse(null);
    }


    public CountryDto createCountry(final CountryDto countryDto) {
        countryDto.setId(null);
        return countryRepository.save(countryDto);
    }

    public CountryDto updateCountry(final Long countryId, final CountryDto updatedCountryDto) {
        final CountryDto country = countryRepository.findById(countryId).orElse(null);
        if (country == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Country with id %s does not exist", countryId));
        }
        updatedCountryDto.setId(country.getId());
        return countryRepository.save(updatedCountryDto);
    }

    public void deleteCountry(final Long countryId) {
        final CountryDto toBeDeletedDto = this.getCountryById(countryId);
        if (toBeDeletedDto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("The country with %s does not exist in the database", countryId));
        }
        countryRepository.delete(toBeDeletedDto);
    }
}

