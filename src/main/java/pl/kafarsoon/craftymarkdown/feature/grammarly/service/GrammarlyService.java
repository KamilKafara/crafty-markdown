package pl.kafarsoon.craftymarkdown.feature.grammarly.service;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.kafarsoon.craftymarkdown.feature.grammarly.service.DTO.GrammarlyDTO;
import pl.kafarsoon.craftymarkdown.feature.grammarly.service.DTO.ItemDTO;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
public class GrammarlyService implements Serializable {
    @Value("${grammarly.api.url}")
    private String grammarBotUrl;

    @Value("${grammarly.api.key}")
    private String grammarBotApiKey;

    public GrammarlyDTO findMistakeFromSentence(String sentence) throws IOException {
        CloseableHttpResponse response = getPostResponse(sentence);
        String entity = EntityUtils.toString(response.getEntity());

        JSONArray matches = new JSONObject(entity).getJSONArray("matches");
        GrammarlyDTO grammarlyDTO = new GrammarlyDTO();
        grammarlyDTO.setSentence(sentence);
        List<ItemDTO> replacementsList = new ArrayList<>();
        for (Object object : matches) {
            JSONObject jsonObject = (JSONObject) object;
            JSONArray replacements = jsonObject.getJSONArray("replacements");
            for (Object replacement : replacements) {
                replacementsList.add(selectReplacements(replacement));
            }
        }
        grammarlyDTO.setReplacements(replacementsList);
        return grammarlyDTO;
    }

    private ItemDTO selectReplacements(Object replacement) {
        JSONObject replacementJson = (JSONObject) replacement;
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setValue(replacementJson.get("value").toString());
        return itemDTO;
    }

    private CloseableHttpResponse getPostResponse(String sentence) throws IOException {
        HttpPost post = new HttpPost(grammarBotUrl);
        List<NameValuePair> urlParameters = setupUrlParameters(sentence);
        post.setEntity(new UrlEncodedFormEntity(urlParameters));
        CloseableHttpClient httpClient = HttpClients.createDefault();
        return httpClient.execute(post);
    }

    private List<NameValuePair> setupUrlParameters(String sentence) {
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("api_key", grammarBotApiKey));
        urlParameters.add(new BasicNameValuePair("language", "en-US"));
        urlParameters.add(new BasicNameValuePair("text", sentence));
        return urlParameters;
    }

}
