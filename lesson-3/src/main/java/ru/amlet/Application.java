package ru.amlet;

import ru.amlet.client.HTTPClient;
import ru.amlet.server.HTTPServer;

public class Application {

    public static void main(String[] args) {
        HTTPServer server = new HTTPServer("localhost", 8080, "/lesson");
        server.start();
        HTTPClient client = new HTTPClient("http://localhost:8080/lesson", "{\"login\": \"java\", \"password\":  \"pwd\"}");
        client.asynchronousGetRequest();
    }
}
