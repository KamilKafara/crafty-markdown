package pl.kafarsoon.craftymarkdown.feature.suggestion.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.kafarsoon.craftymarkdown.feature.suggestion.dto.SuggestionDTO;
import pl.kafarsoon.craftymarkdown.feature.suggestion.repository.Suggestion;

@Service
public class SuggestionTransformer {

    public SuggestionDTO convertToDTO(Suggestion suggestion) {
        return new ModelMapper().map(suggestion, SuggestionDTO.class);
    }

    public Suggestion convertFromDTO(SuggestionDTO suggestionDTO) {
        return new ModelMapper().map(suggestionDTO, Suggestion.class);
    }

}
