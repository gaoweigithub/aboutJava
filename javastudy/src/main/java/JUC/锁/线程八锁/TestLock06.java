package JUC.锁.线程八锁;

/**
 *  * 6. 修改两个方法均为静态同步方法，一个 Number 对象?  //one   two
 */
public class TestLock06 {
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
class test06{
    public static void main(String[] args) {
        final TestLock06 testLock = new TestLock06();

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