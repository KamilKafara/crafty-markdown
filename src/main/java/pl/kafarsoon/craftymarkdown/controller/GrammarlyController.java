package pl.kafarsoon.craftymarkdown.controller;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kafarsoon.craftymarkdown.feature.grammarly.service.DTO.GrammarlyDTO;
import pl.kafarsoon.craftymarkdown.feature.grammarly.service.GrammarlyService;

import java.io.IOException;

@RestController
@RequestMapping("/grammarly")
public class GrammarlyController {

    private final GrammarlyService grammarlyService;

    public GrammarlyController(GrammarlyService grammarlyService) {
        this.grammarlyService = grammarlyService;
    }

    @PostMapping("/{sentence}")
    public GrammarlyDTO getText(@PathVariable String sentence) throws IOException {
        return grammarlyService.findMistakeFromSentence(sentence);
    }
}
