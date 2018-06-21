package JUC.ThreadLocal;

public class Test {
    ThreadLocal<Long> longThreadLocal = new ThreadLocal<>();
    ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();

    public void set() {
        longThreadLocal.set(Thread.currentThread().getId());
        stringThreadLocal.set(Thread.currentThread().getName());
    }

    public Long getLong() {
        return longThreadLocal.get();
    }

    public String getString() {
        return stringThreadLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        final Test test = new Test();

        test.set();
        System.out.println(test.getLong());
        System.out.println(test.getString());

//        Thread thread1 = new Thread(() -> {
//            test.set();
//            System.out.println(test.getLong());
//            System.out.println(test.getString());
//        });
//        thread1.start();
//        thread1.join();

        System.out.println(test.getLong());
        System.out.println(test.getString());
    }
}
