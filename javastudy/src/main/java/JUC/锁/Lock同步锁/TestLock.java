package JUC.锁.Lock同步锁;

public class TestLock {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(ticket, "1号窗口").start();
        new Thread(ticket, "2号窗口").start();
        new Thread(ticket, "3号窗口").start();
        new Thread(ticket, "4号窗口").start();
    }
}
