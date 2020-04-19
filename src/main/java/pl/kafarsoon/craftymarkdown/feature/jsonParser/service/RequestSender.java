package pl.kafarsoon.craftymarkdown.feature.jsonParser.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import pl.kafarsoon.craftymarkdown.feature.lotto.dto.LottoGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class RequestSender {

    private static final String url = "http://serwis.mobilotto.pl/mapi_v6/index.php?json=getGames";
    public JSONObject jsonObject;

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public JSONObject sendRequest() throws IOException {
        URL myurl = new URL(url);
        HttpURLConnection con = (HttpURLConnection) myurl.openConnection();
        con.setRequestMethod("GET");

        StringBuilder content;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String line;
            content = new StringBuilder();
            while ((line = in.readLine()) != null) {
                content.append(line);
                content.append(System.lineSeparator());
            }
        }
        jsonObject = new JSONObject(content.toString());
        return jsonObject;
    }

    public JSONObject getLottoGame(LottoGame lottoGame) {
        return jsonObject.getJSONObject(lottoGame.toString());
    }

}
