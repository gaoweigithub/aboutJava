package JUC.锁.Lock同步锁;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockWithConditon implements Runnable {
    public static ReentrantLock lock = new ReentrantLock(true);
    public static Condition condition = lock.newCondition();

    @Override
    public void run() {
        lock.newCondition();
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "-线程开始等待...");
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockWithConditon test = new ReentrantLockWithConditon();
        Thread t = new Thread(test, "线程ABC");
        t.start();
        Thread.sleep(5000);
        System.out.println("过了1s后。。。");
        lock.lock();
        condition.signal();
        lock.unlock();
    }
}
