package pl.kafarsoon.craftymarkdown.feature.suggestion.service;

import org.springframework.stereotype.Service;
import pl.kafarsoon.craftymarkdown.feature.suggestion.dto.SuggestionDTO;
import pl.kafarsoon.craftymarkdown.feature.suggestion.repository.SuggestionRepository;

import java.util.List;

@Service
public class SuggestionService {

    private final SuggestionRepository suggestionRepository;

    public SuggestionService(SuggestionRepository suggestionRepository) {
        this.suggestionRepository = suggestionRepository;
    }

    public List<SuggestionDTO> getAll() {
        return null;
    }

    public SuggestionDTO getById(Long id) {
        return null;
    }

    public SuggestionDTO getByName(String name) {
        return null;
    }

}
