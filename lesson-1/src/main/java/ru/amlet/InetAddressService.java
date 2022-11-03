package ru.amlet;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static java.net.InetAddress.getLocalHost;

public class InetAddressService {

    private final PrintService printService;

    public InetAddressService(PrintService printService) {
        this.printService = printService;
    }

    public void workWithInetAddress() {
        try {
            InetAddress inetAddress = getLocalHost();
            printService.print("Хост называется: " + inetAddress.getHostName());
            printService.printSeparator();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
