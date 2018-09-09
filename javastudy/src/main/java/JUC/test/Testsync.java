package JUC.test;

public class Testsync implements Runnable {
    int b = 100;

    synchronized void m1() throws InterruptedException {
        b = 1000;
        Thread.sleep(1000);
        System.out.println("b=" + b);
    }

    synchronized void m2() throws InterruptedException {
        Thread.sleep(500);
        b = 2000;
    }

    public static void main(String[] args) throws InterruptedException {
        Testsync ttt = new Testsync();
        Thread t = new Thread(ttt);
        t.start();

        ttt.m2();
        System.out.println("main thread b =" + ttt.b);
    }

    @Override
    public void run() {
        try {
            m1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
