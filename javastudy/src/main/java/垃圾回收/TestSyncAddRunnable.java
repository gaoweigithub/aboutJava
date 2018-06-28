package 垃圾回收;

public class TestSyncAddRunnable {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new SyncRunnable(1, 2)).start();
            new Thread(new SyncRunnable(2, 1)).start();
        }
    }

    public static class SyncRunnable implements Runnable {
        int a;

        public SyncRunnable(int a, int b) {
            this.a = a;
            this.b = b;
        }

        int b;

        @Override
        public void run() {
            synchronized (Integer.valueOf(a)) {
                synchronized (Integer.valueOf(b)) {
                    System.out.println(a + b);
                }
            }
        }
    }
}
