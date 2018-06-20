package JUC.生产者消费者;

public class TestProductorAndConsumer {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();

        Productor pro = new Productor(clerk);
        Consumer cus = new Consumer(clerk);

        new Thread(pro, "生产者 A").start();
        new Thread(cus, "消费者 B").start();

        new Thread(pro, "生产者 C").start();
        new Thread(cus, "消费者 D").start();
    }
}
