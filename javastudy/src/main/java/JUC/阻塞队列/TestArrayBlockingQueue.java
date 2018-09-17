package JUC.阻塞队列;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 　　ArrayBlockingQueue：基于数组实现的一个阻塞队列，在创建ArrayBlockingQueue对象时必须制定容量大小。
 * 并且可以指定公平性与非公平性，默认情况下为非公平的，即不保证等待时间最长的队列最优先能够访问队列。
 */
public class TestArrayBlockingQueue {
    public static void main(String[] args) {
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(5, true);
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    String value = String.valueOf(new Date().getTime());
                    arrayBlockingQueue.put(value);
                    System.out.println("生产" + value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 500);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    String pollstr = arrayBlockingQueue.poll(1000, TimeUnit.MILLISECONDS);
                    System.out.println("consumed :" + pollstr);
                } catch (InterruptedException e) {
                    System.out.println("consume timeout");
                }
            }
        }, 0, 1000);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("队列长度:" + arrayBlockingQueue.size());
            }
        }, 0, 500);

    }
}
