package JUC.闭锁CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch ：闭锁，在完成某些运算时，只有其他所有线程的运算全部完成，当前运算才继续执行
 *     比如计算五十个线程结束运算后的运行时间
 */
public class TestCoundDownLatch {
    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(50);
        LatchDemo demo = new LatchDemo(latch);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 50; i++) {
            new Thread(demo).start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗费时间:" + (end - start));
    }
}
