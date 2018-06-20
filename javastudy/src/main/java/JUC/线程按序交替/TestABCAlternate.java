package JUC.线程按序交替;

public class TestABCAlternate {
    public static void main(String[] args) {
        final AlternateDemo ad = new AlternateDemo();

        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 1; i <= 10; i++) {
                    ad.loopA(i);
                }

            }
        }, "A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 1; i <= 10; i++) {
                    ad.loopB(i);
                }

            }
        }, "B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 1; i <= 10; i++) {
                    ad.loopC(i);

                    System.out.println("-----------------------------------");
                }

            }
        }, "C").start();
    }
}
