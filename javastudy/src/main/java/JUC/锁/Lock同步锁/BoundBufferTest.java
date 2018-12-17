package JUC.锁.Lock同步锁;

public class BoundBufferTest {
    public static void main(String[] args) {
        BoundedBuffer boundedBuffer = new BoundedBuffer();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    boundedBuffer.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("producing:" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                while (true) {
                    Object obj = boundedBuffer.take();
                    System.out.println("consuming:" + obj);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();
        thread2.start();
    }
}
