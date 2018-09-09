package ThreadTest;

/**
 * 死锁之后只能杀死jvm了  interrupp无法停止死锁
 */
public class Uninterruptible {
    public static void main(String[] args) throws InterruptedException {
        final Object o1 = new Object();
        final Object o2 = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (o1) {
                try {
                    Thread.sleep(1000);
                    synchronized (o2) {
                    }
                } catch (InterruptedException e) {
                    System.out.println("t1 interrupeted");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (o2) {
                try {
                    Thread.sleep(1000);
                    synchronized (o1) {
                    }
                } catch (InterruptedException e) {
                    System.out.println("t2 interrupted");
                }
            }
        });

        t1.start();
        t2.start();
        Thread.sleep(1000);
        t1.interrupt();
        t2.interrupt();
        t1.join();
        t2.join();
    }
}
