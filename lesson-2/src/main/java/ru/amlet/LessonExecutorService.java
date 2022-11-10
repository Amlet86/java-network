package ru.amlet;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;
import java.util.Optional;

public class LessonExecutorService {

    private final ReadService readService;
    private final PrintService printService;

    public LessonExecutorService(ReadService readService, PrintService printService) {
        this.readService = readService;
        this.printService = printService;
    }

    public void execute() {
        String url = getURL();
        URLConnection connection = getConnection(url).orElseThrow();
        printResult(connection);
    }

    private String getURL() {
        printService.print("Введите адрес HTTP ресурса и нажмите Enter");
        return readService.read();
    }

    private Optional<URLConnection> getConnection(String url) {
        URLConnection connection = null;
        try {
            URL address = new URL(url);
            connection = address.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(connection);
    }

    private void printResult(URLConnection connection) {
        if (Objects.nonNull(connection)) {
            printService.print("Размер введенного ресурса: " + connection.getContentLength());
            printService.print("Тип введенного ресурса: " + connection.getContentType());
            printService.print("Содержимое введенного ресурса: " + System.lineSeparator());
            try {
                printService.print(connection.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
