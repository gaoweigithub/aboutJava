package NIO.Reactor.ThreadPool.HandlerState;

import NIO.Reactor.ThreadPool.TCPHandler;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ThreadPoolExecutor;

public interface HandlerState {
    void changeState(TCPHandler handler);

    void handle(TCPHandler handler, SelectionKey key, SocketChannel sc, ThreadPoolExecutor pool) throws IOException;
}
