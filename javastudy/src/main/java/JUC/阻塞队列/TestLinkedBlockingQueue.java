package JUC.阻塞队列;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class TestLinkedBlockingQueue {
    public static void main(String[] args) {
        LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>(5);
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    String value = String.valueOf(new Date().getTime());
                    linkedBlockingQueue.put(value);
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
                    String pollstr = linkedBlockingQueue.poll(1000, TimeUnit.MILLISECONDS);
                    System.out.println("consumed :" + pollstr);
                } catch (InterruptedException e) {
                    System.out.println("consume timeout");
                }
            }
        }, 0, 1000);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("队列长度:" + linkedBlockingQueue.size());
            }
        }, 0, 500);

    }
}
