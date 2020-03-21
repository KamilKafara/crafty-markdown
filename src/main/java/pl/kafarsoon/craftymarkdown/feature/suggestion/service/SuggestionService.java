package pl.kafarsoon.craftymarkdown.feature.suggestion.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import pl.kafarsoon.craftymarkdown.exception.handler.ErrorCode;
import pl.kafarsoon.craftymarkdown.exception.handler.FieldInfo;
import pl.kafarsoon.craftymarkdown.feature.suggestion.dto.SuggestionDTO;
import pl.kafarsoon.craftymarkdown.feature.suggestion.repository.Suggestion;
import pl.kafarsoon.craftymarkdown.feature.suggestion.repository.SuggestionRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static pl.kafarsoon.craftymarkdown.exception.handler.ErrorsUtils.badRequestException;
import static pl.kafarsoon.craftymarkdown.exception.handler.ErrorsUtils.notFoundException;

@Service
public class SuggestionService {

    private final SuggestionRepository suggestionRepository;
    private final SuggestionTransformer suggestionTransformer;

    public SuggestionService(SuggestionRepository suggestionRepository, SuggestionTransformer suggestionTransformer) {
        this.suggestionRepository = suggestionRepository;
        this.suggestionTransformer = suggestionTransformer;
    }

    public List<SuggestionDTO> getAll() {
        return suggestionRepository.findAll().stream().map(suggestionTransformer::convertToDTO).collect(Collectors.toList());
    }

    public SuggestionDTO getById(Long id) {
        Optional<Suggestion> suggestionOptional = suggestionRepository.findById(id);
        if (suggestionOptional.isEmpty()) {
            notFoundException("Not found suggestion with this id " + id + " .", new FieldInfo("id", ErrorCode.NOT_FOUND));
        }
        return suggestionTransformer.convertToDTO(suggestionOptional.get());
    }

    public List<SuggestionDTO> getByName(String name) {
        List<Suggestion> suggestionOptional = suggestionRepository.findByName(name);
        return suggestionOptional.stream().map(suggestionTransformer::convertToDTO).collect(Collectors.toList());
    }

    public SuggestionDTO addSuggestion(@Validated SuggestionDTO suggestionDTO) {
        Suggestion suggestion = suggestionTransformer.convertFromDTO(suggestionDTO);
        return suggestionTransformer.convertToDTO(suggestionRepository.save(suggestion));
    }

    public SuggestionDTO updateSuggestion(@Validated SuggestionDTO suggestionDTO, Long id) {
        if (!suggestionDTO.getId().equals(id)) {
            badRequestException("Ids are not equals", new FieldInfo("id", ErrorCode.BAD_REQUEST));
        }
        SuggestionDTO foundSuggestion = getById(id);
        suggestionDTO.setId(foundSuggestion.getId());
        Suggestion suggestion = suggestionTransformer.convertFromDTO(suggestionDTO);

        return suggestionTransformer.convertToDTO(suggestionRepository.save(suggestion));
    }

    public void deleteSuggestionById(Long id) {
        suggestionRepository.deleteById(id);
    }


}
