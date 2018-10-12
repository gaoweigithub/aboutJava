package NIO.Reactor.主从Reactor.HandlerState;

import NIO.Reactor.主从Reactor.TCPHandler;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ThreadPoolExecutor;

public interface HandlerState {
    void changeState(TCPHandler handler);

    void handle(TCPHandler handler, SelectionKey key, SocketChannel sc, ThreadPoolExecutor pool) throws IOException;
}
