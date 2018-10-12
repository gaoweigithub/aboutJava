package NIO.Reactor.ThreadPool.HandlerState;

import NIO.Reactor.ThreadPool.TCPHandler;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ThreadPoolExecutor;

public class WriteState implements HandlerState {
    @Override
    public void changeState(TCPHandler handler) {
        handler.setState(new ReadState());
    }

    @Override
    public void handle(TCPHandler handler, SelectionKey key, SocketChannel sc, ThreadPoolExecutor pool) throws IOException {
        String str = "your message has sent to :" + sc.socket().getRemoteSocketAddress().toString() + "\r\n";
        ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
        while (buffer.hasRemaining()) {
            sc.write(buffer);
        }
        handler.setState(new ReadState());//改變狀態(SENDING->READING)
        key.interestOps(SelectionKey.OP_READ);//通過key改變通道註冊的事件
        key.selector().wakeup();//使一個阻塞住的selector操作立即返回
    }
}
