package NIO.Reactor.ThreadPool;

import NIO.Reactor.ThreadPool.HandlerState.HandlerState;
import NIO.Reactor.ThreadPool.HandlerState.ReadState;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TCPHandler implements Runnable {
    private final SelectionKey key;
    private final SocketChannel sc;
    private static final int THREAD_COUNTING = 10;
    private static ThreadPoolExecutor pool = new ThreadPoolExecutor(THREAD_COUNTING, THREAD_COUNTING, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());


    HandlerState state;

    public TCPHandler(SelectionKey key, SocketChannel sc) {
        this.key = key;
        this.sc = sc;
        state = new ReadState();
        pool.setMaximumPoolSize(32);
    }


    @Override
    public void run() {
        try {
            state.handle(this, key, sc, pool);
        } catch (IOException e) {
            System.out.println("[Warning!] A client has been closed.");
            closeChannel();
        }
    }

    public void closeChannel() {
        try {
            key.cancel();
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setState(HandlerState state) {
        this.state = state;
    }

//    private void process(String str) {
//        System.out.println("process:" + str);
//    }

//    private synchronized void read() throws IOException {
//        byte[] arr = new byte[1024];
//        ByteBuffer buf = ByteBuffer.wrap(arr);
//
//        int numBytes = sc.read(buf);
//        if (numBytes == -1) {
//            System.out.println("[Warning!] A client has been closed.");
//            closeChannel();
//            return;
//        }
//
//        String str = new String(arr);
//        if (str != null) {
//            process(str);
//            System.out.println(sc.socket().getRemoteSocketAddress().toString() + ">" + str);
//            state = 1;
//            key.interestOps(SelectionKey.OP_WRITE);//通過key改變通道註冊的事件
//            key.selector().wakeup();//使一個阻塞住的selector操作立即返回
//        }
//    }
//
//    private void send() throws IOException {
//        String str = "Your message has sent to " + sc.socket().getRemoteSocketAddress().toString() + "\r\n";
//        ByteBuffer buf = ByteBuffer.wrap(str.getBytes());
//        while (buf.hasRemaining()) {
//            sc.write(buf);
//        }
//        state = 0;
//        key.interestOps(SelectionKey.OP_READ);
//        key.selector().wakeup();
//    }
}
