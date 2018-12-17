package JUC.锁.线程八锁;

/**
 *  * 2. 新增 Thread.sleep() 给 getOne() ,打印? //one  two
 */
public class TestLock02 {
    public synchronized void getOne(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("one");
    }

    public synchronized void getTwo(){
        System.out.println("two");
    }
}
class test02{
    public static void main(String[] args) {
        final TestLock02 testLock = new TestLock02();
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