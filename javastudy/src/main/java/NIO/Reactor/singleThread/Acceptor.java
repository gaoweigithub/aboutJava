package NIO.Reactor.singleThread;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Acceptor implements Runnable {
    private final ServerSocketChannel ssc;
    private final Selector selector;

    public Acceptor(ServerSocketChannel ssc, Selector selector) {
        this.ssc = ssc;
        this.selector = selector;
    }

    @Override
    public void run() {
        try {
            SocketChannel sc = ssc.accept();
            System.out.println(sc.socket().getRemoteSocketAddress().toString() + "is connected.");
            if (sc != null) {
                sc.configureBlocking(false);
                SelectionKey key = sc.register(selector, SelectionKey.OP_READ);
                selector.wakeup();
                key.attach(new TCPHandler(key, sc));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
