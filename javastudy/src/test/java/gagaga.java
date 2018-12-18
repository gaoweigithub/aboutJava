import datetime.date;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class gagaga {
    public static void main(String[] args) {
        SimpleDateFormat sdf=  new SimpleDateFormat("yyyy-MM-ddTHH:mm:ss");
        System.out.println(sdf.format(new date()));

    }
}
