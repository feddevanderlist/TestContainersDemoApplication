package nl.vandalist.service;

import nl.vandalist.model.LanguageDto;
import nl.vandalist.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class LanguageService {
    @Autowired
    private LanguageRepository languageRepository;

    public List<LanguageDto> getLanguages() {
        return StreamSupport.stream(languageRepository.findAll().spliterator(), false).toList();
    }

    public LanguageDto getLanguageById(final Long languageId) {
        return languageRepository.findById(languageId).orElse(null);
    }

    public LanguageDto createLanguage(final LanguageDto languageDto) {
        List<LanguageDto> byNameLanguage = languageRepository.findLanguagesByNameContainingIgnoreCase(languageDto.getName());
        Optional<LanguageDto> byIdLanguage = languageRepository.findById(languageDto.getId());
        if (!byNameLanguage.isEmpty()) {
            return byNameLanguage.get(0);
        } else return byIdLanguage.orElseGet(() -> languageRepository.save(languageDto));
    }

    public LanguageDto updateLanguage(final Long languageId, final LanguageDto languageDto) {
        LanguageDto oldLanguageDto = languageRepository.findById(languageId).orElse(null);
        if (oldLanguageDto == null) {
            return null;
        }
        oldLanguageDto.setName(languageDto.getName());
        return languageRepository.save(oldLanguageDto);
    }

    public void deleteLanguage(final Long languageId) {
        final LanguageDto toBeDeletedDto = this.getLanguageById(languageId);
        if (toBeDeletedDto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("The language with %s does not exist in the database", languageId));
        }
        languageRepository.delete(toBeDeletedDto);
    }
}
