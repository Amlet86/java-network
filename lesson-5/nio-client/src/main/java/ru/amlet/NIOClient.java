package ru.amlet;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class NIOClient {

    public static void main(String[] args) throws IOException {

        try (SocketChannel socketChannel =
                     SocketChannel.open(new InetSocketAddress("localhost", 8080))) {

            ByteBuffer buffer = ByteBuffer.wrap("Hello".getBytes());
            socketChannel.write(buffer);
            buffer.clear();

            socketChannel.read(buffer);
            System.out.println(new String(buffer.array(), 0, buffer.position(), StandardCharsets.UTF_8).trim());
            buffer.clear();

            buffer.put("Stop".getBytes());
            socketChannel.write(buffer);
        }
    }
}
