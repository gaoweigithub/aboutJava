package JUC.Condition控制线程通信;

public class TestProductorAndConsumerForLock {
    public static void main(String[] args) throws InterruptedException {
        Clerk clerk = new Clerk();

        Productor productor = new Productor(clerk);
        Consumer consumer = new Consumer(clerk);

        new Thread(productor, "生产者A").start();
        Thread.sleep(200);
        new Thread(consumer, "消费者B").start();

    }
}
