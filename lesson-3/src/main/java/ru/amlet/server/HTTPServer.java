package ru.amlet.server;

import com.sun.net.httpserver.HttpServer;
import ru.amlet.handler.HTTPExchangeHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class HTTPServer {

    private final String hostName;
    private final int port;
    private final String path;

    public HTTPServer(String hostName, int port, String path) {
        this.hostName = hostName;
        this.port = port;
        this.path = path;
    }

    public void start() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(hostName, port), 0);
            server.setExecutor(Executors.newFixedThreadPool(10));
            server.createContext(path, new HTTPExchangeHandler());
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
