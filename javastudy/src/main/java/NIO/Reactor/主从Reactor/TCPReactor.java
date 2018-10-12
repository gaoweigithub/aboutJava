package NIO.Reactor.主从Reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class TCPReactor implements Runnable {
    private final ServerSocketChannel ssc;
    private final Selector selector;

    public TCPReactor(int port) throws IOException {
        selector = Selector.open();
        ssc = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(port);
        ssc.socket().bind(address);
        ssc.configureBlocking(false);
        SelectionKey key = ssc.register(selector, SelectionKey.OP_ACCEPT);
        key.attach(new Acceptor(ssc));
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            System.out.println("waiting for new event on port :" + ssc.socket().getLocalPort() + "....");
            try {
                if (selector.select() == 0) {
                    continue;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> it = selectionKeys.iterator();
            while (it.hasNext()) {
                dispatch(it.next());
                it.remove();
            }
        }
    }

    /*
     * name: dispatch(SelectionKey key)
     * description: 調度方法，根據事件綁定的對象開新線程
     */
    private void dispatch(SelectionKey key) {
        Runnable r = (Runnable) key.attachment();
        if (r != null) {
            r.run();
        }
    }
}
