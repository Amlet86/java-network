package ru.amlet;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import static java.net.InetAddress.getLocalHost;
import static java.net.NetworkInterface.getNetworkInterfaces;

public class LessonExecutor {

    private final PrintService printService = new PrintService();

    public void execute() {
        workWithInetAddress();
        workWithNetworkInterface();
    }

    private void workWithInetAddress() {
        try {
            InetAddress inetAddress = getLocalHost();
            printService.print("Хост называется: " + inetAddress.getHostName());
            printService.printSeparator();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private void workWithNetworkInterface() {
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
