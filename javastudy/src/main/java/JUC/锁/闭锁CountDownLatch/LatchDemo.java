package JUC.锁.闭锁CountDownLatch;

import java.util.concurrent.CountDownLatch;

public class LatchDemo implements Runnable {
    private CountDownLatch countDownLatch;

    public LatchDemo(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        synchronized (this) {
            try {
                for (int i = 0; i < 100; i++) {
                    if (i % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + "--->" + i);
                    }
                }
            } finally {
                countDownLatch.countDown();
            }
        }
    }
}
