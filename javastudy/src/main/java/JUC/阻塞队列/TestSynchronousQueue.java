package JUC.阻塞队列;

import java.util.concurrent.SynchronousQueue;

public class TestSynchronousQueue {
    public static void main(String[] args) {
        SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue(true);

        Thread threadProduce = new Thread(() -> {
            for (int i = 0; ; i++) {
                try {
                    synchronousQueue.put(i);
                    System.out.println("producing:" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadConsume = new Thread(() -> {
            for (; ; ) {
                Integer takeValue = null;
                try {
                    takeValue = synchronousQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("get:" + takeValue);
            }
        });
        threadProduce.start();
        threadConsume.start();
    }
}
