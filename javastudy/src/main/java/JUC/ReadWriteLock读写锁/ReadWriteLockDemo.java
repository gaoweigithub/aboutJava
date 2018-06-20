package JUC.ReadWriteLock读写锁;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    private int number = 0;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    //读取
    public void get() {
        lock.readLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + " : " + number);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放读锁
            lock.readLock().unlock();
        }
    }

    //写锁
    public void set(int number) {
        lock.writeLock().lock();

        try {
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName());
            this.number = number;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }
}
