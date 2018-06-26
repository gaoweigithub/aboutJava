package 异常;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class AutoClosableTest {
    public static void main(String[] args) throws FileNotFoundException {

        /**
         * 类似于C#的using语法
         */
        try (Scanner in = new Scanner(new FileInputStream("qwe"));
             PrintWriter out = new PrintWriter("out.txt")) {
            while (in.hasNext()) {
                out.print(in.next().toUpperCase());
            }
        }
    }
}
