package 垃圾回收;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ThreadWaitingTest {
    public static void createBusyThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) ;
            }
        }, "testBusyThread").start();
    }

    public static void createLockThread(final Object lock) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "testLockThread").start();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        createBusyThread();
        br.readLine();
        Object object = new Object();
        createLockThread(object);
    }
}
