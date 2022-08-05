package nl.vandalist.service;

import nl.vandalist.model.LanguageDto;
import nl.vandalist.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<LanguageDto> getLanguageByName(final String languageName) {
        return languageRepository.findLanguageDtosByNameContainingIgnoreCase(languageName);
    }

    public LanguageDto createLanguage(final LanguageDto languageDto) {
        List<LanguageDto> byNameLanguage = languageRepository.findLanguageDtosByNameContainingIgnoreCase(languageDto.getName());
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
}
