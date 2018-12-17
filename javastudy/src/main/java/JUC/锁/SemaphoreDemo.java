package JUC.锁;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    private Semaphore smp = new Semaphore(3, true);
    private Random rnd = new Random();

    class TaskDemo implements Runnable {
        private String id;

        TaskDemo(String id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                smp.acquire();
                System.out.println("thread " + id + " is working ");
                Thread.sleep(rnd.nextInt(1000));
                smp.release();
                System.out.println("thread " + id + " is over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SemaphoreDemo semaphoreDemo = new SemaphoreDemo();
        ExecutorService se = Executors.newCachedThreadPool();
        se.submit(semaphoreDemo.new TaskDemo("a"));
        se.submit(semaphoreDemo.new TaskDemo("b"));
        se.submit(semaphoreDemo.new TaskDemo("c"));
        se.submit(semaphoreDemo.new TaskDemo("d"));
        se.submit(semaphoreDemo.new TaskDemo("e"));
        se.submit(semaphoreDemo.new TaskDemo("f"));
        se.submit(semaphoreDemo.new TaskDemo("g"));
        se.submit(semaphoreDemo.new TaskDemo("h"));
        se.submit(semaphoreDemo.new TaskDemo("i"));
        se.submit(semaphoreDemo.new TaskDemo("j"));
        se.submit(semaphoreDemo.new TaskDemo("k"));
        se.shutdown();
    }
}
