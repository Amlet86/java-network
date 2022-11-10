package ru.amlet;

public class Main {

    public static void main(String[] args) {
        ReadService readService = new ReadService(System.in);
        PrintService printService = new PrintService(System.out);
        LessonExecutorService lessonExecutorService = new LessonExecutorService(readService, printService);
        lessonExecutorService.execute();
    }
}
