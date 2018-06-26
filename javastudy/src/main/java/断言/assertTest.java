package 断言;

import java.io.Console;

public class assertTest {
    public static void main(String[] args) {
        int ii=0;
        assert ii != 0: "123123";
        Double rr = Double.valueOf(10/ii);
        System.out.println(rr);
    }
}
