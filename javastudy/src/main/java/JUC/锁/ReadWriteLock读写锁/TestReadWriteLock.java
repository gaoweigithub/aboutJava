package JUC.锁.ReadWriteLock读写锁;

public class TestReadWriteLock {
    public static void main(String[] args) {
        final ReadWriteLockDemo rw = new ReadWriteLockDemo();

        new Thread(() -> rw.set((int) (Math.random() * 101))).start();

        new Thread(() -> rw.set((int) (Math.random() * 101))).start();

        for (int i = 0; i < 100; i++) {
            new Thread(() -> rw.get()).start();
        }
    }
}
