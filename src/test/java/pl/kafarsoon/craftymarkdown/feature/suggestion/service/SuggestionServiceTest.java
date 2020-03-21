package pl.kafarsoon.craftymarkdown.feature.suggestion.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.kafarsoon.craftymarkdown.exception.BadRequestException;
import pl.kafarsoon.craftymarkdown.exception.NotFoundException;
import pl.kafarsoon.craftymarkdown.feature.suggestion.dto.SuggestionDTO;
import pl.kafarsoon.craftymarkdown.feature.suggestion.repository.Suggestion;
import pl.kafarsoon.craftymarkdown.feature.suggestion.repository.SuggestionRepository;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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
        when(suggestionTransformer.convertToDTO(suggestion)).thenReturn(suggestionDTO);
        SuggestionDTO expectedSuggestionDTO = suggestionService.getById(id);
        //then
        verify(suggestionRepository, times(1)).findById(id);
        verify(suggestionTransformer, times(1)).convertToDTO(suggestion);
        assertEquals(expectedSuggestionDTO.getId(), suggestionDTO.getId());
        assertEquals(expectedSuggestionDTO.getName(), suggestionDTO.getName());
    }

    @Test
    public void getById_should_throw_not_found_suggestion_exception() {
        //given
        Long id = 1L;
        //when
        Throwable throwable = catchThrowable(() -> suggestionService.getById(id));
        //then
        verify(suggestionRepository, times(1)).findById(id);
        verify(suggestionTransformer, times(0)).convertToDTO(any(Suggestion.class));
        assertThat(throwable).isInstanceOf(NotFoundException.class);
        assertThat(throwable).hasMessage("Not found suggestion with this id " + id + " .");
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
    public void addSuggestion_should_return_saved_object() {
        //given
        SuggestionDTO suggestionDTO = new SuggestionDTO("Faker");
        Suggestion suggestion = new Suggestion("Faker");
        //when
        when(suggestionTransformer.convertFromDTO(any(SuggestionDTO.class))).thenReturn(suggestion);
        when(suggestionRepository.save(suggestion)).thenReturn(suggestion);
        when(suggestionTransformer.convertToDTO(suggestion)).thenReturn(suggestionDTO);
        SuggestionDTO addedSuggestion = suggestionService.addSuggestion(suggestionDTO);
        //then
        verify(suggestionTransformer, times(1)).convertFromDTO(suggestionDTO);
        verify(suggestionRepository, times(1)).save(suggestion);
        verify(suggestionTransformer, times(1)).convertToDTO(suggestion);
        assertEquals(addedSuggestion.getId(), suggestionDTO.getId());
        assertEquals(addedSuggestion.getName(), suggestionDTO.getName());
    }

    @Test
    public void addSuggestion_should_throw_bad_request_exception_not_null_id() {
        //given
        SuggestionDTO suggestionDTO = new SuggestionDTO(1L, "Faker");
        //when
        Throwable throwable = catchThrowable(() -> suggestionService.addSuggestion(suggestionDTO));
        //then
        assertThat(throwable).isInstanceOf(BadRequestException.class);
        assertThat(throwable).hasMessage("Id must be null.");
        verify(suggestionRepository, times(0)).save(any(Suggestion.class));
    }

    @Test
    public void updateSuggestion_should_update_and_return_update_object() {
        //given
        Long id = 1L;
        SuggestionDTO suggestionDTO = new SuggestionDTO(1L, "Faker");
        Suggestion suggestion = new Suggestion(1L, "Faker");

        //when
        when(suggestionRepository.findById(id)).thenReturn(java.util.Optional.of(suggestion));
        when(suggestionTransformer.convertToDTO(suggestion)).thenReturn(suggestionDTO);
        when(suggestionRepository.save(suggestion)).thenReturn(suggestion);
        when(suggestionTransformer.convertFromDTO(suggestionDTO)).thenReturn(suggestion);

        SuggestionDTO expectedSuggestionDTO = suggestionService.updateSuggestion(suggestionDTO, id);
        //then
        verify(suggestionTransformer, times(1)).convertFromDTO(suggestionDTO);
        verify(suggestionRepository, times(1)).save(suggestion);
        verify(suggestionTransformer, times(2)).convertToDTO(suggestion);
        assertEquals(expectedSuggestionDTO.getId(), suggestionDTO.getId());
        assertEquals(expectedSuggestionDTO.getName(), suggestionDTO.getName());

    }

    @Test
    public void updateSuggestion_should_throw_bad_request_exception_ids_not_equal() {
        //given
        Long id = 2L;
        SuggestionDTO suggestionDTO = new SuggestionDTO(1L, "Faker");
        //when
        Throwable throwable = catchThrowable(() -> suggestionService.updateSuggestion(suggestionDTO, id));
        //then
        assertThat(throwable).isInstanceOf(BadRequestException.class);
        assertThat(throwable).hasMessage("Ids are not equals");
    }

    @Test
    public void deleteSuggestionById_should_passed() {
        //given
        Long id = 1L;
        SuggestionDTO suggestionDTO = new SuggestionDTO(1L, "Faker");
        Suggestion suggestion = new Suggestion(1L, "Faker");
        //when
        when(suggestionRepository.findById(id)).thenReturn(java.util.Optional.of(suggestion));
        when(suggestionTransformer.convertToDTO(suggestion)).thenReturn(suggestionDTO);
        suggestionService.deleteSuggestionById(id);
        //then
        verify(suggestionRepository, times(1)).findById(id);
        verify(suggestionTransformer, times(1)).convertToDTO(suggestion);
        verify(suggestionRepository, times(1)).deleteById(id);
    }

    @Test
    public void deleteSuggestionById_should_throw_not_found_suggestion_exception() {
        //given
        Long id = 1L;
        //when
        Throwable throwable = catchThrowable(() -> suggestionService.deleteSuggestionById(id));
        //then
        verify(suggestionRepository, times(1)).findById(id);
        verify(suggestionTransformer, times(0)).convertToDTO(any(Suggestion.class));
        verify(suggestionRepository, times(0)).deleteById(id);
        assertThat(throwable).isInstanceOf(NotFoundException.class);
        assertThat(throwable).hasMessage("Not found suggestion with this id " + id + " .");
    }
}
