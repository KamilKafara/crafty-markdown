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
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
public class GrammarlyService implements Serializable {

    public List<String> getText(String text) throws IOException, JSONException {
        HttpPost post = new HttpPost("http://api.grammarbot.io/v2/check");

        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("api_key", "XYZ"));
        urlParameters.add(new BasicNameValuePair("language", "en-US"));
        urlParameters.add(new BasicNameValuePair("text", text));
        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(post);
        String entity = EntityUtils.toString(response.getEntity());
        JSONArray matches = new JSONObject(entity).getJSONArray("matches");
        for (Object object : matches) {
            JSONObject jsonObject = (JSONObject) object;
            JSONArray replacements = jsonObject.getJSONArray("replacements");
            for (Object replacement : replacements) {
                JSONObject replacementJson = (JSONObject) replacement;
                System.out.println(replacementJson.get("value"));
            }
        }

        return null;
    }
}
