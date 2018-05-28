package JUC.线程八锁;

/**
 * 7. 一个静态同步方法，一个非静态同步方法，两个 Number 对象?  //two  one
 */
public class TestLock07 {
    public static synchronized void getOne(){
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

class test07{
    public static void main(String[] args) {
        final TestLock07 testLock01 = new TestLock07();
        final TestLock07 testLock02 = new TestLock07();

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