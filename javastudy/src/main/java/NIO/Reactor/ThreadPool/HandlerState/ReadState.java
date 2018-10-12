package NIO.Reactor.ThreadPool.HandlerState;

import NIO.Reactor.ThreadPool.TCPHandler;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ThreadPoolExecutor;

public class ReadState implements HandlerState {
    private SelectionKey key;

    public ReadState() {
    }

    @Override
    public void changeState(TCPHandler handler) {
        handler.setState(new WorkState());
    }

    @Override
    public void handle(TCPHandler handler, SelectionKey key, SocketChannel sc, ThreadPoolExecutor pool) throws IOException {
        this.key = key;
        byte[] arr = new byte[1024];
        ByteBuffer buffer = ByteBuffer.wrap(arr);
        int numBytes = sc.read(buffer);
        if (numBytes == -1) {
            System.out.println("[Warning!] A client has been closed.");
            handler.closeChannel();
            return;
        }
        String str = new String(arr);
        if (str != null) {
            handler.setState(new WorkState());
            pool.execute(new WorkerThread(handler, str));
            System.out.println(sc.socket().getRemoteSocketAddress().toString() + ">" + str);
        }
    }

    synchronized void process(TCPHandler handler, String str) {
        handler.setState(new WriteState());
        this.key.interestOps(SelectionKey.OP_WRITE);
        this.key.selector().wakeup();
        System.out.println("processing:" + str + "\r\n");
    }

    class WorkerThread implements Runnable {
        TCPHandler handler;
        String str;

        @Override
        public void run() {
            process(handler, str);
        }

        public WorkerThread(TCPHandler handler, String str) {
            this.handler = handler;
            this.str = str;
        }
    }
}
