package ru.amlet;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import static java.net.NetworkInterface.getNetworkInterfaces;

public class NetworkInterfaceService {

    private final PrintService printService;

    public NetworkInterfaceService(PrintService printService) {
        this.printService = printService;
    }

    public void workWithNetworkInterface() {
        try {
            Enumeration<NetworkInterface> networkInterfaceEnumeration = getNetworkInterfaces();

            while (networkInterfaceEnumeration.hasMoreElements()) {
                String interfaceName = networkInterfaceEnumeration.nextElement().getName();
                printService.print("Интерфейс называется: " + interfaceName);

                NetworkInterface networkInterface = NetworkInterface.getByName(interfaceName);
                printService.print("Интерфейс поднят: " + networkInterface.isUp());
                printService.print("Интерфейс виртуальный: " + networkInterface.isVirtual());
                printService.print("Интерфейс loopback: " + networkInterface.isLoopback());
                printService.printSeparator();
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

}
