package datetime;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class calendar {
    public static void main(String[] args) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(2018, 5, 22, 22, 28, 59);
        System.out.println(calendar.getTime());
        System.out.println(calendar.get(Calendar.MONTH));
        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println(calendar.getTimeZone());

        System.out.println(calendar.get(Calendar.HOUR));
        calendar.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
        System.out.println(calendar.get(Calendar.HOUR));
    }
}
