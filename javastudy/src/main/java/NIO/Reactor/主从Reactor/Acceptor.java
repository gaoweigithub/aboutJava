package NIO.Reactor.主从Reactor;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Acceptor implements Runnable {
    private final ServerSocketChannel ssc;
    private final int cores = Runtime.getRuntime().availableProcessors();
    private final Selector[] selectors = new Selector[cores];
    private int selIdex = 0;
    private TCPSubReactor[] r = new TCPSubReactor[cores];
    private Thread[] t = new Thread[cores];


    public Acceptor(ServerSocketChannel ssc) throws IOException {
        this.ssc = ssc;

        for (int i = 0; i < cores; i++) {
            selectors[i] = Selector.open();
            r[i] = new TCPSubReactor(ssc, selectors[i], i);
            t[i] = new Thread(r[i]);
            t[i].start();
        }
    }

    @Override
    public synchronized void run() {
        try {
            SocketChannel sc = ssc.accept();
            System.out.println(sc.socket().getRemoteSocketAddress().toString() + " is connected.");

            if (sc != null) {
                sc.configureBlocking(false);
                r[selIdex].setRestart(true);
                selectors[selIdex].wakeup();
                SelectionKey key = sc.register(selectors[selIdex], SelectionKey.OP_READ);
                selectors[selIdex].wakeup();
                r[selIdex].setRestart(false);
                key.attach(new TCPHandler(key, sc));
                if (++selIdex == selectors.length) {
                    selIdex = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
