package JUC.锁.线程八锁;

/**
 *  * 5. 修改 getOne() 为静态同步方法，打印?  //two   one
 */
public class TestLock05 {
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

class test05{
    public static void main(String[] args) {
        final TestLock05 testLock = new TestLock05();

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
