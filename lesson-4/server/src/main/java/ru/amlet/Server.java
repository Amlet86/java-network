package ru.amlet;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {

    public static void main(String[] args) throws IOException {
        try (DatagramSocket socket = new DatagramSocket(8080)) {
            byte[] buf = new byte[256];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);

            while (true) {
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());

                if ("Stop".equalsIgnoreCase(received)) {
                    break;
                }

                socket.send(new DatagramPacket(buf, buf.length, packet.getAddress(), packet.getPort()));
            }
        }
    }
}
