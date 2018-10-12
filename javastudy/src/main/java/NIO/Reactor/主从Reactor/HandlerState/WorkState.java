package NIO.Reactor.主从Reactor.HandlerState;

import NIO.Reactor.主从Reactor.TCPHandler;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ThreadPoolExecutor;

public class WorkState implements HandlerState {
    @Override
    public void changeState(TCPHandler handler) {
        handler.setState(new WriteState());
    }

    @Override
    public void handle(TCPHandler handler, SelectionKey key, SocketChannel sc, ThreadPoolExecutor pool) throws IOException {

    }
}
