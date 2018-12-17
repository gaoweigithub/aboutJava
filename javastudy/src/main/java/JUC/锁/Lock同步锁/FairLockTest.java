package JUC.锁.Lock同步锁;

import java.util.concurrent.locks.ReentrantLock;

public class FairLockTest implements Runnable {
    /**
     * t1和t2交替获取到锁。如果是非公平锁，会发生t1运行了许多遍后t2才开始运行的情况。
     */
    public static ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                System.err.println(Thread.currentThread().getName() + "获取到了锁！");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        FairLockTest test = new FairLockTest();
        Thread t1 = new Thread(test, "线程1");
        Thread t2 = new Thread(test, "线程2");
        t1.start();
        t2.start();
    }
}
