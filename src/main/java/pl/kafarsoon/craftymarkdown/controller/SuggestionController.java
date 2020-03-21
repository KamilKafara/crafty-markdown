package pl.kafarsoon.craftymarkdown.controller;

import org.springframework.web.bind.annotation.*;
import pl.kafarsoon.craftymarkdown.feature.suggestion.dto.SuggestionDTO;
import pl.kafarsoon.craftymarkdown.feature.suggestion.service.SuggestionService;

import java.util.List;

@RequestMapping("/suggestion")
@RestController
public class SuggestionController {
    private final SuggestionService suggestionService;

    public SuggestionController(SuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }

    @GetMapping
    public List<SuggestionDTO> getAll() {
        return suggestionService.getAll();
    }

    @GetMapping("{id}")
    public SuggestionDTO getById(@PathVariable("id") Long id) {
        return suggestionService.getById(id);
    }

    @GetMapping("{name}")
    public List<SuggestionDTO> getAllByName(@PathVariable("name") String name) {
        return suggestionService.getByName(name);
    }

    @PostMapping("{id}")
    public SuggestionDTO update(@RequestBody SuggestionDTO suggestionDTO, @PathVariable("id") Long id) {
        return suggestionService.updateSuggestion(suggestionDTO, id);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable("id") Long id) {
        suggestionService.deleteSuggestionById(id);
    }


}
