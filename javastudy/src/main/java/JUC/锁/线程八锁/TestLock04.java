package JUC.锁.线程八锁;

/**
 *  * 4. 两个普通同步方法，两个 Number 对象，打印?  //two  one
 */
public class TestLock04 {
    public  synchronized void getOne(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }

        System.out.println("one");
    }
    public synchronized void getTwo(){//this
        System.out.println("two");
    }
}
class test04{
    public static void main(String[] args) {
        final TestLock04 number1 = new TestLock04();
        final TestLock04 number2 = new TestLock04();

        new Thread(new Runnable() {
            @Override
            public void run() {
                number1.getOne();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                number2.getTwo();
            }
        }).start();
    }
}
