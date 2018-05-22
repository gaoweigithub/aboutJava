package finaltest;

import java.util.Date;

public class finaltest {
    private final Date date;

    public finaltest() {
        date = new Date();
    }


    public void processDate(Date nd) {
        /**
         * final对象初始化之后不能更改引用，但可以对其操作
         */
//        this.date = nd;
        date.setTime(new Date().getTime());
        System.out.println(date);
    }
}
