package testRedisLock;

public class testLock {
    private static volatile int add = 10;

    public static void main(String[] args) {
//        deAdderWithNoLock();
//        deAdderWithLock_1();
//        deAdderWithLock_2();
        deAdderWithLock_3();
    }

    private static void deAdderWithLock_3() {
        Runnable handler = () -> {
            RedisLock lock = new RedisLock("lock003");
            if (lock.lock_3(30000)) {
                while (add > 0) {
                    System.out.println(Thread.currentThread().toString() + " ———————— add@" + add);
                    add--;
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.unlock();
            }

        };
        new Thread(handler).start();
        new Thread(handler).start();
        new Thread(handler).start();
    }

    private static void deAdderWithLock_2() {
        Runnable handler = () -> {
            RedisLock lock = new RedisLock("lock002");
            if (lock.lock_2(30000)) {
                while (add > 0) {
                    System.out.println(Thread.currentThread().toString() + " ———————— add@" + add);
                    add--;
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.unlock();
            }

        };
        new Thread(handler).start();
        new Thread(handler).start();
        new Thread(handler).start();
    }

    private static void deAdderWithLock_1() {
        Runnable handler = () -> {
            RedisLock lock = new RedisLock("lock001");
            if (lock.lock(30000)) {
                while (add > 0) {
                    System.out.println(Thread.currentThread().toString() + " ———————— add@" + add);
                    add--;
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.unlock();
            }

        };
        new Thread(handler).start();
        new Thread(handler).start();
        new Thread(handler).start();
    }

    private static void deAdderWithNoLock() {
        Runnable handler = () -> {
            while (add > 0) {
                System.out.println(Thread.currentThread().toString() + " ———————— add@" + add);
                add--;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(handler).start();
        new Thread(handler).start();
        new Thread(handler).start();
    }
}
