package NIO.Reactor.主从Reactor;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Sub Reactor在实作上有个重点要注意，
 * <p>
 * 当一个监听中而阻塞住的selector由于Acceptor需要注册新的IO事件到该selector上时，
 * <p>
 * Acceptor会调用selector的wakeup()函数唤醒阻塞住的selector，以注册新IO事件后再继续监听。
 * <p>
 * 但Sub Reactor中循环调用selector.select()的线程回圈可能会因为循环太快，导致selector被唤醒后再度于IO事件成功注册前被调用selector.select()而阻塞住，
 * <p>
 * 因此我们需要给Sub Reactor线程循环设置一个flag来控制，
 * <p>
 * 让selector被唤醒后不会马上进入下回合调用selector.select()的Sub Reactor线程循环，
 * <p>
 * 等待我们将新的IO事件注册完之后才能让Sub Reactor线程继续运行。
 * ---------------------
 * 作者：StackOverFlower
 * 来源：CSDN
 * 原文：https://blog.csdn.net/weililansehudiefei/article/details/70889544?utm_source=copy
 * 版权声明：本文为博主原创文章，转载请附上博文链接！
 */
public class TCPSubReactor implements Runnable {
    private final ServerSocketChannel ssc;
    private final Selector selector;
    private boolean restart = false;
    private int num;

    public TCPSubReactor(ServerSocketChannel ssc, Selector selector, int num) {
        this.ssc = ssc;
        this.selector = selector;
        this.num = num;
    }


    @Override
    public void run() {
        while (!Thread.interrupted()) {
            System.out.println("waiting for restart");
            while (!Thread.interrupted() && !restart) {
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

    public void setRestart(boolean restart) {
        this.restart = restart;
    }
}
