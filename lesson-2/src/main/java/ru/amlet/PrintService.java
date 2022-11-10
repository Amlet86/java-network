package ru.amlet;

import java.io.*;

public class PrintService {

    private final PrintStream output;

    public PrintService(PrintStream output) {
        this.output = output;
    }

    public void print(String string) {
        output.println(string);
    }

    public void print(InputStream inputStream) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                output.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
