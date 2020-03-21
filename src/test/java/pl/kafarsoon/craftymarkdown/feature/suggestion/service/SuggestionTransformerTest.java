package pl.kafarsoon.craftymarkdown.feature.suggestion.service;

import com.github.javafaker.Faker;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import pl.kafarsoon.craftymarkdown.feature.suggestion.dto.SuggestionDTO;
import pl.kafarsoon.craftymarkdown.feature.suggestion.repository.Suggestion;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class SuggestionTransformerTest {

    @InjectMocks
    private SuggestionTransformer transformer;
    private Faker faker = new Faker();

    @org.junit.Test
    public void convertToDTO() {
        //given
        Suggestion suggestion = new Suggestion();
        suggestion.setId(1L);
        suggestion.setName(faker.funnyName().name());
        //when
        SuggestionDTO expectedSuggestion = transformer.convertToDTO(suggestion);
        //then
        assertEquals(expectedSuggestion.getId(), suggestion.getId());
        assertEquals(expectedSuggestion.getName(), suggestion.getName());
    }

    @org.junit.Test
    public void convertFromDTO() {
        //given
        SuggestionDTO suggestionDTO = new SuggestionDTO();
        suggestionDTO.setId(1L);
        suggestionDTO.setName(faker.funnyName().name());
        //when
        Suggestion expectedSuggestion = transformer.convertFromDTO(suggestionDTO);
        //then
        assertEquals(expectedSuggestion.getId(), suggestionDTO.getId());
        assertEquals(expectedSuggestion.getName(), suggestionDTO.getName());
    }
}
