package ru.amlet;

import java.io.InputStream;
import java.util.Scanner;

public class ReadService {

    private final Scanner input;

    public ReadService(InputStream inputStream) {
        this.input = new Scanner(inputStream);
    }

    public String read() {
        return input.nextLine();
    }
}
