package JUC.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {
    private final DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public Date parse(String s) throws ParseException {
        return format.parse(s);
    }

    public static void main(String[] args) {
        DateParser parser = new DateParser();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            Runnable runnable = () -> {
                try {
                    System.out.println(parser.parse("2018-08-28"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            };
            runnable.run();
        }
    }
}
