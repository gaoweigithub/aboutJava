package JUC.ThreadLocal;

public class Son implements Cloneable {
    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            public void run() {
                ThreadLocal<Son> threadLocal1 = new ThreadLocal<>();
                threadLocal1.set(new Son());
                System.out.println(threadLocal1.get());
                ThreadLocal<Son> threadLocal2 = new ThreadLocal<>();
                threadLocal2.set(new Son());
                System.out.println(threadLocal2.get());
            }
        });
        t.start();
    }
}