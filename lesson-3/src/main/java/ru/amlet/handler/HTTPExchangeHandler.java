package ru.amlet.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ru.amlet.exception.InvalidLoginException;
import ru.amlet.exception.InvalidRequestException;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class HTTPExchangeHandler implements HttpHandler {

    private final static String EXPECTED_REQUEST = "\"login\": \"java\"";
    private final static String EXPECTED_RESPONSE = "Hello Java";

    @Override
    public void handle(HttpExchange exchange) {
        if ("POST".equals(exchange.getRequestMethod())) {
            handleRequest(exchange);
        } else {
            throw new UnsupportedOperationException("Unsupported HTTP Method: " + exchange.getRequestMethod());
        }
    }

    private void handleRequest(HttpExchange exchange) {
        String request = getStringRequest(exchange).orElseThrow();
        checkRequest(request);
        try (OutputStream responseBody = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, EXPECTED_RESPONSE.length());
            responseBody.write(EXPECTED_RESPONSE.getBytes(StandardCharsets.UTF_8));
            responseBody.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Optional<String> getStringRequest(HttpExchange exchange) {
        String request = null;
        try {
            request = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(request);
    }

    private void checkRequest(String request) {
        try {
            if (!request.contains(EXPECTED_REQUEST)) {
                String errorMessage = request.split(":")[1].split(",")[0];
                throw new InvalidLoginException(errorMessage);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidRequestException(request);
        }
    }

}
