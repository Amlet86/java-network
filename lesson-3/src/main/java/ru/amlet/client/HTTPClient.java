package ru.amlet.client;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HTTPClient {

    private final String uri;
    private final String requestBody;

    public HTTPClient(String uri, String requestBody) {
        this.uri = uri;
        this.requestBody = requestBody;
    }

    public void asynchronousGetRequest() {
        HttpClient client = HttpClient.newHttpClient();
        URI httpURI = null;
        try {
            httpURI = new URI(uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpRequest request = HttpRequest.newBuilder(httpURI)
                .version(HttpClient.Version.HTTP_2)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(resp -> System.out.println(resp.body()));
    }
}
