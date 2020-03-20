package pl.kafarsoon.craftymarkdown.feature.grammarly.service.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class GrammarlyDTO {
    private String sentence;
    private List<ItemDTO> replacements;
}
