package ru.amlet;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class NIOServer {

    public static void main(String[] args) throws IOException {

        try (Selector selector = Selector.open();
             ServerSocketChannel serverSocket = ServerSocketChannel.open()) {

            serverSocket.bind(new InetSocketAddress("localhost", 8080));
            serverSocket.configureBlocking(false);
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);

            ByteBuffer buffer = ByteBuffer.allocate(256);
            boolean keepOnWorking = true;

            while (keepOnWorking) {
                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isAcceptable()) {
                        accept(selector, serverSocket);
                    } else if (key.isReadable()) {
                        keepOnWorking = processRequest(buffer, key);
                    }
                    iterator.remove();
                }
            }
        }
    }

    private static void accept(Selector selector, ServerSocketChannel serverSocket) throws IOException {
        SocketChannel client = serverSocket.accept();
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
    }

    private static boolean processRequest(ByteBuffer buffer, SelectionKey key) throws IOException {

        SocketChannel client = (SocketChannel) key.channel();
        buffer.clear();
        client.read(buffer);

        if (buffer.hasArray()) {
            String payload = new String(buffer.array(), 0, buffer.position(), StandardCharsets.UTF_8).trim();

            if ("stop".equalsIgnoreCase(payload)) {
                client.close();
                return false;
            }

            buffer.flip();
            client.write(buffer);
        }

        return true;
    }
}
