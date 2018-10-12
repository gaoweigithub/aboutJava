package NIO.Reactor.singleThread;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class TCPHandler implements Runnable {
    private final SelectionKey key;
    private final SocketChannel sc;
    int state;

    public TCPHandler(SelectionKey key, SocketChannel sc) {
        this.key = key;
        this.sc = sc;
        state = 0;
    }


    @Override
    public void run() {
        try {
            if (state == 0) {
                read();
            } else {
                send();
            }
        } catch (IOException e) {
            System.out.println("[Warning!] A client has been closed.");
            e.printStackTrace();
        }
    }

    private void closeChannel() {
        try {
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void process(String str) {
        System.out.println("process:" + str);
    }

    private synchronized void read() throws IOException {
        byte[] arr = new byte[1024];
        ByteBuffer buf = ByteBuffer.wrap(arr);

        int numBytes = sc.read(buf);
        if (numBytes == -1) {
            System.out.println("[Warning!] A client has been closed.");
            closeChannel();
            return;
        }

        String str = new String(arr);
        if (str != null) {
            process(str);
            System.out.println(sc.socket().getRemoteSocketAddress().toString() + ">" + str);
            state = 1;
            key.interestOps(SelectionKey.OP_WRITE);//通過key改變通道註冊的事件
            key.selector().wakeup();//使一個阻塞住的selector操作立即返回
        }
    }

    private void send() throws IOException {
        String str = "Your message has sent to " + sc.socket().getRemoteSocketAddress().toString() + "\r\n";
        ByteBuffer buf = ByteBuffer.wrap(str.getBytes());
        while (buf.hasRemaining()) {
            sc.write(buf);
        }
        state = 0;
        key.interestOps(SelectionKey.OP_READ);
        key.selector().wakeup();
    }
}
