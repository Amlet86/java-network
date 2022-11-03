package ru.amlet;

public class Main {

    public static void main(String[] args) {
        PrintService printService = new PrintService();
        InetAddressService inetAddressService = new InetAddressService(printService);
        NetworkInterfaceService networkInterfaceService = new NetworkInterfaceService(printService);
        LessonExecutorService lessonExecutorService =
                new LessonExecutorService(inetAddressService, networkInterfaceService);
        lessonExecutorService.execute();
    }
}
