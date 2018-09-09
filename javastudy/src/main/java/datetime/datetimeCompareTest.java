package datetime;

import java.util.Date;

public class datetimeCompareTest {
    public static void main(String[] args) throws InterruptedException {
        Date d1 = new Date();
        Thread.sleep(1000);
        Date d2 = new Date();
        System.out.println(d2.getTime() - d1.getTime());
    }
}
