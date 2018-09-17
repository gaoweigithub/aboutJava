package JUC.阻塞队列;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

public class TestDelayQueue {
    public static void main(String[] args) {
        DelayQueue<User> delayQueue = new DelayQueue<>();
        Random random = new Random();

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    Integer value = random.nextInt(100);
                    delayQueue.add(new User(value.intValue(),TimeUnit.MILLISECONDS,value.intValue()));
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
                    User pollstr = delayQueue.poll();
                    if (pollstr != null){
                        System.out.println("消费 :" + pollstr.getUserID());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, 0, 1000);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("队列长度 :" + delayQueue.size());
            }
        }, 0, 500);
    }
}
