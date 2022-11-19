package ru.amlet;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        try (DatagramSocket socket = new DatagramSocket(); Scanner scanner = new Scanner(System.in)) {
            InetAddress address = InetAddress.getByName("localhost");

            while (true) {
                String input = scanner.nextLine();
                if ("Stop".equalsIgnoreCase(input)) {
                    break;
                }

                byte[] buf = input.getBytes();
                DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 8080);
                socket.send(packet);
                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println(received);
            }
        }
    }
}
