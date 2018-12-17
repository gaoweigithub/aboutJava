package JUC.锁.线程八锁;

/**
 *  * 3. 新增普通方法 getThree() , 打印? //three  one   two
 */
public class TestLock03 {
    public synchronized void getOne(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }

        System.out.println("one");
    }
    public synchronized void getTwo(){
        System.out.println("two");
    }
    public void getThree(){
        System.out.println("three");
    }
}
class test03{
    public static void main(String[] args) {
        final TestLock03 testLock = new TestLock03();
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

        new Thread(new Runnable() {
            @Override
            public void run() {
                testLock.getThree();
            }
        }).start();
    }
}