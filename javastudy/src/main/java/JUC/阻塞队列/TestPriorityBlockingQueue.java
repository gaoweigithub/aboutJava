package JUC.阻塞队列;

import java.util.PriorityQueue;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class TestPriorityBlockingQueue {
    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        Random random = new Random();
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    Integer value = random.nextInt(100);
                    priorityQueue.add(value);
                    System.out.println("生产 :" + value);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, 0, 500);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    Integer pollstr = priorityQueue.poll();
                    System.out.println("消费 :" + pollstr);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, 0, 1000);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("队列长度 :" + priorityQueue.size());
            }
        }, 0, 500);
    }
}
