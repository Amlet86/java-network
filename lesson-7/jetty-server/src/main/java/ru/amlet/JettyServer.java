package ru.amlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.server.handler.AbstractHandler;

import java.io.IOException;

public class JettyServer {

    public static void main(String[] args) throws Exception {

        Server server = new Server();
        HttpConfiguration httpConfig = new HttpConfiguration();
        httpConfig.setSendServerVersion(false);
        HttpConnectionFactory http = new HttpConnectionFactory(httpConfig);
        ServerConnector connector = new ServerConnector(server, http);
        connector.setPort(8080);
        server.addConnector(connector);

        server.setHandler(new AbstractHandler() {

            @Override
            public void handle(String target, Request jettyRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {

                if ("/service".equals(target)) {
                    System.out.println(jettyRequest.getPathInfo());
                    response.getWriter().write("Hello " + request.getParameter("name"));
                }
                jettyRequest.setHandled(true);
            }
        });

        server.start();

    }
}
