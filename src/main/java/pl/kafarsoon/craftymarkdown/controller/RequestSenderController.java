package pl.kafarsoon.craftymarkdown.controller;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import pl.kafarsoon.craftymarkdown.feature.jsonParser.service.RequestSender;
import pl.kafarsoon.craftymarkdown.feature.lotto.dto.LottoGame;

import java.io.IOException;

@RestController
@RequestMapping("/sender")
public class RequestSenderController {

    private final RequestSender requestSender;

    public RequestSenderController(RequestSender requestSender) {
        this.requestSender = requestSender;
    }

    @GetMapping
    public JSONObject getMethod() throws IOException {
        return requestSender.sendRequest();
    }

    @GetMapping("/{game}")
    public JSONObject getMethod(@PathVariable("game") LottoGame lottoGame) throws IOException {
        requestSender.sendRequest();
        return requestSender.getLottoGame(lottoGame);
    }

    @PostMapping
    public void postMethod() {

    }

    @PutMapping
    public void putMethod() {

    }

    @DeleteMapping
    public void deleteMethod() {

    }
}
