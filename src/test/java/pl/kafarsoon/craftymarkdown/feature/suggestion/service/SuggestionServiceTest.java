package pl.kafarsoon.craftymarkdown.feature.suggestion.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.kafarsoon.craftymarkdown.exception.NotFoundException;
import pl.kafarsoon.craftymarkdown.feature.suggestion.dto.SuggestionDTO;
import pl.kafarsoon.craftymarkdown.feature.suggestion.repository.Suggestion;
import pl.kafarsoon.craftymarkdown.feature.suggestion.repository.SuggestionRepository;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SuggestionServiceTest {

    @InjectMocks
    private SuggestionService suggestionService;
    @Mock
    private SuggestionTransformer suggestionTransformer;
    @Mock
    private SuggestionRepository suggestionRepository;


    @Test
    public void getAll_should_return_one_object() {
        //given
        SuggestionDTO suggestionDTO = new SuggestionDTO(1L, "Faker");
        Suggestion suggestion = new Suggestion(1L, "Faker");

        List<SuggestionDTO> expectedSuggestionList = Collections.singletonList(suggestionDTO);
        List<Suggestion> expectedSuggestions = Collections.singletonList(suggestion);

        //when
        when(suggestionRepository.findAll()).thenReturn(expectedSuggestions);
        when(suggestionTransformer.convertToDTO(any(Suggestion.class))).thenReturn(expectedSuggestionList.get(0));
        List<SuggestionDTO> suggestionDTOList = suggestionService.getAll();

        //then
        verify(suggestionRepository, times(1)).findAll();
        verify(suggestionTransformer, times(expectedSuggestionList.size())).convertToDTO(suggestion);

        assertEquals(expectedSuggestionList.size(), suggestionDTOList.size());
        assertEquals(expectedSuggestionList.get(0).getId(), suggestionDTOList.get(0).getId());
        assertEquals(expectedSuggestionList.get(0).getName(), suggestionDTOList.get(0).getName());
    }

    @Test
    public void getById_should_return_one_object() {
        //given
        Long id = 1L;
        SuggestionDTO suggestionDTO = new SuggestionDTO(1L, "Faker");
        Suggestion suggestion = new Suggestion(1L, "Faker");
        //when
        when(suggestionRepository.findById(id)).thenReturn(java.util.Optional.of(suggestion));
        when(suggestionTransformer.convertToDTO(any(Suggestion.class))).thenReturn(suggestionDTO);
        SuggestionDTO expectedSuggestionDTO = suggestionService.getById(id);
        //then
        verify(suggestionRepository, times(1)).findById(id);
        verify(suggestionTransformer, times(1)).convertToDTO(suggestion);
        assertEquals(expectedSuggestionDTO.getId(), suggestionDTO.getId());
        assertEquals(expectedSuggestionDTO.getName(), suggestionDTO.getName());
    }

    @Test
    public void getById_should_throw_not_found_exception() {
        //given
        Long id = 1L;
        //when
        Throwable throwable = catchThrowable(() -> suggestionService.getById(id));
        //then
        verify(suggestionRepository, times(1)).findById(id);
        verify(suggestionTransformer, times(0)).convertToDTO(any(Suggestion.class));

        Assertions.assertThat(throwable).isInstanceOf(NotFoundException.class);
        Assertions.assertThat(throwable).hasMessage("Not found suggestion with this id " + id + " .");
    }

    @Test
    public void getByName_should_return_list() {
        //given
        String name = "Faker";
        SuggestionDTO suggestionDTO = new SuggestionDTO(1L, name);
        Suggestion suggestion = new Suggestion(1L, name);
        List<SuggestionDTO> expectedSuggestionList = Collections.singletonList(suggestionDTO);
        List<Suggestion> expectedSuggestions = Collections.singletonList(suggestion);

        //when
        when(suggestionRepository.findByName(name)).thenReturn(expectedSuggestions);
        when(suggestionTransformer.convertToDTO(any(Suggestion.class))).thenReturn(expectedSuggestionList.get(0));
        List<SuggestionDTO> suggestionDTOList = suggestionService.getByName(name);

        //then
        verify(suggestionRepository, times(1)).findByName(name);
        verify(suggestionTransformer, times(expectedSuggestionList.size())).convertToDTO(suggestion);

        assertEquals(expectedSuggestionList.size(), suggestionDTOList.size());
        assertEquals(expectedSuggestionList.get(0).getId(), suggestionDTOList.get(0).getId());
        assertEquals(expectedSuggestionList.get(0).getName(), suggestionDTOList.get(0).getName());
    }

    @Test
    public void addSuggestion() {
        //given

        //when

        //then
    }

    @Test
    public void updateSuggestion() {
        //given

        //when

        //then
    }

    @Test
    public void deleteSuggestionById() {
        //given

        //when

        //then
    }
}
