package pl.kafarsoon.craftymarkdown.feature.suggestion.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SuggestionDTO {
    private Long id;
    @NotNull
    private String name;
}
