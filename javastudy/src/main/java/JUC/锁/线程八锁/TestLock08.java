package JUC.锁.线程八锁;

/**
 * 8. 两个静态同步方法，两个 Number 对象?   //one  two
 */
public class TestLock08 {
    public static synchronized void getOne(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("one");
    }
    public static synchronized void getTwo(){
        System.out.println("two");
    }
}

class test08{
    public static void main(String[] args) {
        final TestLock08 testLock01 = new TestLock08();
        final TestLock08 testLock02 = new TestLock08();

        new Thread(new Runnable() {
            @Override
            public void run() {
                testLock01.getOne();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                testLock02.getTwo();
            }
        }).start();
    }
}