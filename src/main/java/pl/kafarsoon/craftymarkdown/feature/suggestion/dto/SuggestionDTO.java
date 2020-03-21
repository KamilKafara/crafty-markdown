package pl.kafarsoon.craftymarkdown.feature.suggestion.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
public class SuggestionDTO {
    private Long id;
    @NotNull
    private String name;
}
