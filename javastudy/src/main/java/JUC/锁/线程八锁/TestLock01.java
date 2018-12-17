package JUC.锁.线程八锁;

/**
 * * 1. 两个普通同步方法，两个线程，标准打印， 打印? //one  two
 */
public class TestLock01 {
    public synchronized void getOne() {
        System.out.println("one");
    }

    public synchronized void getTwo() {
        System.out.println("two");
    }
}
class test01{
    public static void main(String[] args) {
        final TestLock01 testLock = new TestLock01();
        new Thread(new Runnable() {
            @Override
            public void run() {
                testLock.getOne();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                testLock.getTwo();
            }
        }).start();
    }
}